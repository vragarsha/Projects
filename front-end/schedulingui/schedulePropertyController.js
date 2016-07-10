'use strict';
/* jshint undef: false */
/* globals d3, moment */

angular.module('analytics').controller('SchedulePropertyController', ['$rootScope', '$scope', '$modalInstance', 'CoreData', 'Analytics', 'Errors', 'rootEquipmentId', 'rootEquipmentName', 'scheduleString', 'user',
    function($rootScope, $scope, $modalInstance, CoreData, Analytics, Errors, rootEquipmentId, rootEquipmentName, scheduleString, user) {

        var now = moment();

        $scope.fullSchedule = {
            scheduleName: '',
            scheduleString: scheduleString ? scheduleString : '1@nn:nn|nn:nn;2@nn:nn|nn:nn;3@nn:nn|nn:nn;4@nn:nn|nn:nn;5@nn:nn|nn:nn;6@nn:nn|nn:nn;7@nn:nn|nn:nn',
            days: [],
            daylightSavings: true,
            offset: now.format('Z'),
            isDST: now.isDST()
        };
        $scope.selectedSchedule = null;
        $scope.selectedRootAssetId = rootEquipmentId;
        $scope.selectedRootAssetName = rootEquipmentName;
        $scope.showNameTextbox = false;
        $scope.sch = {
            AllDay: false,
        };


        $scope.showCustomerSelection = CoreData.isSystemAdmin(user);


        $scope.init = function() {

            if ($scope.selectedRootAssetId) {

                Analytics.getScheduleFavoriteList($scope.selectedRootAssetId).then(function(result) {

                    if (!result.error) {
                        $scope.schedules = result.body;
                    }
                    else {
                        Errors.add(result.errorText);
                    }
                });
            }

            //Add the 7 Days to the schedule object
            for (var n = 0; n < 7; n++) {

                var newDay = {
                    isSelected: false,
                    number: n+1,
                    stringValue: null,
                    windows: []
                };

                $scope.fullSchedule.days.push(newDay);
            }

            decodeScheduleString($scope.fullSchedule.scheduleString);
        };

        $scope.selectedRootAssetChanged = function(rootAsset) {

            if (rootAsset) {

                var parsedAsset = JSON.parse(rootAsset);
                $scope.selectedRootAssetId = parsedAsset.Id;
                $scope.selectedRootAssetName = parsedAsset.DisplayName;

                Analytics.getScheduleFavoriteList($scope.selectedRootAssetId).then(function(result) {
                    
                    if (!result.error) {
                        $scope.schedules = result.body;
                    }
                    else {
                        Errors.add(result.errorText);
                    }
                });
            }
        };

        $scope.selectedScheduleChanged = function(sch) {
            
            if (sch) {

                $scope.selectedSchedule = JSON.parse(sch);
                $scope.fullSchedule.scheduleName = $scope.selectedSchedule.ScheduleDisplayName;
                $scope.fullSchedule.scheduleString = $scope.selectedSchedule.ScheduleDetails;

                decodeScheduleString($scope.selectedSchedule.ScheduleDetails);
            }
            else {
                $scope.selectedSchedule = null;
                $scope.fullSchedule.scheduleName = null;
            }
        };



    /************* Time formatting functions ***************/
        var isValidTimeFormat = function(timeString) {

            var fullFormat = /^([0-9]{1,2})\:([0-9]{2})$/;
            var hourFormat = /^([0-9]{1,2})$/;
            
            if (fullFormat.test(timeString) === true) {

                return true;
            }
            else if (hourFormat.test(timeString) === true) {

                return true;
            }

            return false;
        };

        var parseTime = function(hours, minutes) {

            if (hours.length === 1) {
                hours = '0' + hours;
            }


            if (minutes.length === 1) {
                minutes = '0' + minutes;
            }

            return hours + ':' + minutes;
        };

        var validHourRange = function(hours) {

            if (hours < 0 || hours > 23) {
                return false;
            }

            return true;
        };

        var validMinuteRange = function(minutes) {

            if (minutes < 0 || minutes > 59) {
                return false;
            }

            return true;
        };

        var validEndTime = function(start, end) {

            var startDate = Date.parse('01/01/2016 ' + start);
            var endDate = Date.parse('01,01/2016 ' + end);

            return endDate > startDate;
        };

        var validStartTime = function(currentStart, previousEnd) {

            var currentStartDate = Date.parse('01/01/2016 ' + currentStart);
            var previousEndDate = Date.parse('01,01/2016 ' + previousEnd);

            return currentStartDate > previousEndDate;
        };

        /*var convertHoursToUTC = function(hours) {

            var time = new Date();
            time.setHours(hours);

            var utcHours = time.getUTCHours();

            if (utcHours < 10) {
                utcHours = '0' + utcHours;
            }

            return utcHours;
        };*/

        var encodeScheduleString = function() {

            var resultString = [];
                
            for (var i = 0; i < $scope.fullSchedule.days.length; i++) {

                var currentDay = $scope.fullSchedule.days[i];
                var nextDay = i === 6 ? $scope.fullSchedule.days[0] : $scope.fullSchedule.days[i+1];

                currentDay.utcStringValue = (i+1) + '@';
            
                if (currentDay.windows.length === 0) {

                    currentDay.utcStringValue += 'nn:nn|nn:nn;';
                }
                else {

                    for (var j = 0; j < currentDay.windows.length; j++) {

                        var currentWindow = currentDay.windows[j];

                        if (j > 0) {

                            //Need to add the delimiter
                            currentDay.utcStringValue += '^';
                        }

                        currentDay.utcStringValue += currentWindow.start + '|' + currentWindow.end;

                        if (j === currentDay.windows.length - 1) {

                            //Finish the string
                            currentDay.utcStringValue += ';';
                        }
                    }
                }

                

                resultString.push(currentDay.utcStringValue);
            }

            //Add the Daylight Savings to the string
            resultString.push('DST=' + ($scope.fullSchedule.daylightSavings === true ? '1' : '0'));

            //Add the offset to the string
            resultString.push(';OFFSET=' + $scope.fullSchedule.offset);

            //Check if it is daylight savings
            resultString.push($scope.fullSchedule.isDST === true ? 'D' : 'S');

            return resultString.join('');
        };

        var decodeScheduleString = function(scheduleString) {

            //1. Split the string by days
            var days = scheduleString.split(';');

            //2. Populate each day's windows accordingly
            for (var i = 0; i < 7; i++) {

                var day = days[i].split('@');
                var dayNumber = day[0];
                var dayWindows = day[1].split('^');

                //Clear the current day's windows
                $scope.fullSchedule.days[i].windows.length = 0;

                for (var j in dayWindows) {

                    var currentWindow = dayWindows[j].split('|');
                    var startTime = currentWindow[0];
                    var endTime = currentWindow[1];

                    if (startTime !== 'nn:nn' && endTime !== 'nn:nn') {

                        //Create a new window and add it to the day
                        var newWin = {
                            number: (j+1),
                            start: startTime,
                            end: endTime,
                            value: startTime + '|' + endTime
                        };

                        $scope.fullSchedule.days[i].windows.push(newWin);
                    }
                }
            }

            if (days[7]) {

                //3. Set the Daylight Savings flag
                var isSet = days[7].split('=')[1];
                $scope.fullSchedule.daylightSavings = isSet === '1' ? true : false;
            }

            if (days[8]) {
            
                //4. Set the Offset flag
                var offsetNumber = days[8].split('=')[1];
                $scope.fullSchedule.offset = offsetNumber;
            }
        };
    /************** End of Formatting Methods **************/

    /************* Time Windows Grid ***********************/

        $scope.windows = [
            {number: 1, start: null, end: null, isValid: false},
            {number: 2, start: null, end: null, isValid: false},
            {number: 3, start: null, end: null, isValid: false},
            {number: 4, start: null, end: null, isValid: false}
        ];
        $scope.selectedWindows = [];
        $scope.gridOptions = {
            data: 'windows',
            multiSelect: false,
            enableColumnResize: false,
            enableSorting: false,
            selectedItems: $scope.selectedWindows,
            enableCellSelection: true,
            columnDefs: [
                {field: 'number', displayName: '#', enableCellEdit: false, width: 20},
                {field: 'start', displayName: 'Start', enableCellEdit: true, width: 55},
                {field: 'end', displayName: 'End', enableCellEdit: true, width: 55},
                {field: 'error', displayName: 'Validation', enableCellEdit: false, cellTemplate: './modules/analytics/views/cellTemplates/scheduleTimeValidationCellTemplate.html'}
            ],
            afterSelectionChange: function() {
                $scope.selectedWindow = $scope.selectedWindows[0];
            }
        };

        var processTimeChange = function(timeWindow, col) {

            //1. Check that it is in the correct format
            if (isValidTimeFormat(timeWindow[col]) === false) {

                console.log('Invalid format for ' + col + ' time');
                timeWindow.error = 'Invalid format for ' + col + ' time';
                timeWindow[col] = null;
                timeWindow.isValid = false;
                return;
            }

            //2. Seprate the hours from the minutes
            var split = timeWindow[col].split(':');
            var hours = split[0];
            var minutes = split[1] ? split[1] : '00';

            //3. Check that the hours and minutes are withing range
            if (!validHourRange(hours) || !validMinuteRange(minutes)) {

                console.log('Invalid range for ' + col + ' time');
                timeWindow.error = 'Invalid range for ' + col + ' time';
                timeWindow[col] = null;
                timeWindow.isValid = false;
                return;
            }

            //4. Parse the time
            timeWindow[col] = parseTime(hours, minutes);
        };

        var validateWindow = function(timeWindow) {

            //1. Check that the end time is greater than the start time
            if (validEndTime(timeWindow.start, timeWindow.end) === true) {

                timeWindow.isValid = true;
                timeWindow.error = null;
            }
            else {

                console.log('End time cannot be less than the start time');
                timeWindow.error = 'End time cannot be less than the start time';
                timeWindow.end = null;
                timeWindow.isValid = false;
                return;
            }


            //2. If this is NOT the first window, run checks agains the previous window
            if (timeWindow.number > 1) {

                var previousWindow = $scope.windows[timeWindow.number-2];

                //2.1 Check that the previous window is valid
                if (previousWindow.isValid === false) {

                    timeWindow.error = 'The previous window (' + previousWindow.number + ') is invalid';
                    timeWindow.start = null;
                    timeWindow.end = null;
                    timeWindow.isValid = false;
                    return;
                }


                //2.2 Check that the start time of the current window is greater than the end time
                //of the previous window
                if (validStartTime(timeWindow.start, previousWindow.end) === false) {

                    console.log('Start time cannot be less than the end time of window ' + previousWindow.number);
                    timeWindow.error = 'Overlaping end and start of windows ' + previousWindow.number + ' & ' + timeWindow.number;
                    /*timeWindow.start = null;
                    timeWindow.end = null;*/
                    timeWindow.isValid = false;
                    return;
                }
            }
        };

        $scope.$on('ngGridEventEndCellEdit', function(event) {

            var col = event.targetScope.col.field;
            var timeWindow = event.targetScope.row.entity;

            processTimeChange(timeWindow, col);


            //Now check the start and end times of both the current and previous windows
            if (timeWindow.start && timeWindow.end) {

                validateWindow(timeWindow);
            }
        });

        var clearWindows = function() {
            
            for (var i in $scope.windows) {

                var w = $scope.windows[i];
                w.start = null;
                w.end = null;
                w.isValid = false;
                w.error = null;
            }
        };

        $scope.setTo24Hours = function() {

            $scope.sch.AllDay = !$scope.sch.AllDay;
                                  
            for (var i in $scope.windows) {

                var w = $scope.windows[i];
                w.start = null;
                w.end = null;
                w.isValid = false;
            }

            $scope.windows[0].start = '00:00';
            $scope.windows[0].end = '23:59';
            $scope.windows[0].isValid = true;
        };

        $scope.dtsChanged = function() {

            $scope.fullSchedule.daylightSavings = !$scope.fullSchedule.daylightSavings;
            $scope.fullSchedule.scheduleString = encodeScheduleString();
        };

        $scope.clear = function() {

            for (var i = 0; i < $scope.fullSchedule.days.length; i++) {

                var currentDay = $scope.fullSchedule.days[i];

                if (currentDay.isSelected === true) {

                    currentDay.stringValue = null;
                    currentDay.windows.length = 0;
                    currentDay.isSelected = false;
                }
            }

            clearWindows();
            $scope.sch.AllDay = false;

            $scope.fullSchedule.scheduleString = encodeScheduleString();
        };

        $scope.set = function() {

            //1. Get the valid windows from the grid
            var validWindows = [];
            for (var k in $scope.windows) {

                var win = $scope.windows[k];

                if (win.isValid) {

                    validWindows.push(
                    {
                        number: win.number,
                        start: win.start,
                        end: win.end,
                        value: win.start + '|' + win.end
                    });
                }
            }


            //2. Check to see all the start times and end times are valid
            if (validWindows.length > 1) {
                
                for (var j = 1; j < validWindows.length; j++) {

                    //Make sure that the Start time of the current window
                    //is greater than the End time of the previous window
                    var currentWindow = validWindows[j];
                    var previousWindow = validWindows[j-1];

                    if (validStartTime(currentWindow.start, previousWindow.end) === false) {

                        Errors.add('The Start Time of window ' + currentWindow.number + ' cannot be less than the End Time of window ' + previousWindow.number);
                        return;
                    }
                }
            }

            for (var i = 0; i < $scope.fullSchedule.days.length; i++) {

                var currentDay = $scope.fullSchedule.days[i];

                if (currentDay.isSelected) {

                    currentDay.windows = angular.copy(validWindows);
                    currentDay.isSelected = false;
                }
            }


            $scope.fullSchedule.scheduleString = encodeScheduleString();
            $scope.sch.AllDay = false;
            clearWindows();
        };
    /**************** End of Grid Methods ******************/


        $scope.saveAsFavorite = function() {

            var favoriteData = {
                scheduleString: $scope.fullSchedule.scheduleString,
                scheduleName: $scope.fullSchedule.scheduleName,
                rootEquipmentId: $scope.selectedRootAssetId,
                rootEquipmentName: $scope.selectedRootAssetName
            };

            //If a schedule with the same name exist, then update it, else create it
            var scheduleExists = false;
            var scheduleId = null;
            for (var i in $scope.schedules) {

                if ($scope.schedules[i].ScheduleName === $scope.fullSchedule.scheduleName) {
                    scheduleExists = true;
                    scheduleId = $scope.schedules[i].ScheduleID;
                    break;
                }
            }

            if (scheduleExists) {
                //Update it
                Analytics.updateFavoriteSchedule(favoriteData, scheduleId).then(function(result) {
                
                    if (result.error) {
                        Errors.add(result.errorText);
                    }
                });
            }
            else {
                //Create it
                Analytics.createFavoriteSchedule(favoriteData).then(function(result) {

                    if (!result.error) {

                        Analytics.getScheduleFavoriteList($scope.selectedRootAssetId).then(function(result) {

                            if (!result.error) {
                                $scope.schedules = result.body;
                            }
                        });
                    }
                    else {
                        Errors.add(result.errorText);
                    }
                    
                });
            }
        };

        $scope.deleteFavorite = function() {

            Analytics.deleteFavoriteSchedule($scope.selectedSchedule.ScheduleID).then(function(result) {
                
                if (!result.error) {

                    Analytics.getScheduleFavoriteList($scope.selectedRootAssetId).then(function(result) {

                        if (!result.error) {
                            $scope.schedules = result.body;
                        }
                    });
                }
                else {
                    Errors.add(result.errorText);
                }
            });
        };



        $scope.saveDisabled = function() {

            return false;
        };

        $scope.save = function() {

            $modalInstance.close($scope.fullSchedule);
        };

        $scope.cancel = function() {

            $modalInstance.dismiss('cancel');
        };
    }
]);
