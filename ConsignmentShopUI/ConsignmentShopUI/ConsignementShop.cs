﻿using ConsignementShopLibrary;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ConsignmentShopUI
{
    public partial class ConsignementShop : Form
    {
        private Store store = new Store();
        private List<Item> shoppingCartData = new List<Item>();
        BindingSource itemBinding = new BindingSource();
        BindingSource cartBinding = new BindingSource();
        BindingSource vendorsBinding = new BindingSource();
        private decimal storeProfit = 0;
        public ConsignementShop()
        {
            InitializeComponent();
            SetupData();
            itemBinding.DataSource = store.Items;
            itemsListBox.DataSource = itemBinding;

            itemsListBox.DisplayMember = "Display";
            itemsListBox.ValueMember = "Display";

            cartBinding.DataSource = shoppingCartData;
            shoppingCartListBox.DataSource = cartBinding;

            shoppingCartListBox.DisplayMember = "Display";
            shoppingCartListBox.ValueMember = "Display";

            vendorsBinding.DataSource = store.Vendors;
            vendorListBox.DataSource = vendorsBinding;

            vendorListBox.DisplayMember = "Display";
            vendorListBox.ValueMember = "Display";

        }

        private void SetupData()
        {
            store.Vendors.Add(new Vendor { FirstName = "Bill", LastName = "Smith" });
            store.Vendors.Add(new Vendor { FirstName = "Su", LastName = "James" });
            store.Items.Add(new Item
            {
                Title = "Moby Dick",
                Description = "A book about a whale",
                Price = 4.50M,
                Owner = store.Vendors[0]
            });
            store.Items.Add(new Item
            {
                Title = "My Life",
                Description = "A book about Gerrard",
                Price = 9.50M,
                Owner = store.Vendors[1]
            });
            store.Items.Add(new Item
            {
                Title = "Cr7",
                Description = "A book on a legend",
                Price = 0.50M,
                Owner = store.Vendors[1]
            });
            store.Items.Add(new Item
            {
                Title = "Harry Potter",
                Description = "A book about a boy",
                Price = 14.50M,
                Owner = store.Vendors[0]
            });
            store.Name = "Seconds are Better";

        }

        private void ConsignementShop_Load(object sender, EventArgs e)
        {

        }

        private void addToCart_Click(object sender, EventArgs e)
        {
            Item selectedItem = (Item)itemsListBox.SelectedItem;
            shoppingCartData.Add(selectedItem);
            cartBinding.ResetBindings(false);
        }

        private void makePurchase_Click(object sender, EventArgs e)
        {
            foreach (Item item in shoppingCartData)
            {
                item.Sold = true;
                item.Owner.PaymentDue = (decimal)item.Owner.Commission * item.Price;
                storeProfit = (1 - (decimal)item.Owner.Commission) * item.Price;
            }
            shoppingCartData.Clear();
            itemBinding.DataSource = store.Items.Where(x => x.Sold == false).ToList();
            storeProfitValue.Text = string.Format("${0}", storeProfit);
            cartBinding.ResetBindings(false);
            itemBinding.ResetBindings(false);
            vendorsBinding.ResetBindings(false);
        }
    }
}
