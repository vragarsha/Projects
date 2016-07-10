namespace ConsignmentShopUI
{
    partial class ConsignementShop
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.headerText = new System.Windows.Forms.Label();
            this.itemsList = new System.Windows.Forms.Label();
            this.itemsListBox = new System.Windows.Forms.ListBox();
            this.shoppingCartListBox = new System.Windows.Forms.ListBox();
            this.shoppingCartListBoxLabel = new System.Windows.Forms.Label();
            this.addToCart = new System.Windows.Forms.Button();
            this.makePurchase = new System.Windows.Forms.Button();
            this.vendorListBox = new System.Windows.Forms.ListBox();
            this.vendorListBoxLabel = new System.Windows.Forms.Label();
            this.storeProfitLabel = new System.Windows.Forms.Label();
            this.storeProfitValue = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // headerText
            // 
            this.headerText.AutoSize = true;
            this.headerText.Font = new System.Drawing.Font("Microsoft Sans Serif", 24F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.headerText.Location = new System.Drawing.Point(57, 9);
            this.headerText.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.headerText.Name = "headerText";
            this.headerText.Size = new System.Drawing.Size(423, 37);
            this.headerText.TabIndex = 0;
            this.headerText.Text = "Consignement Shop Demo";
            // 
            // itemsList
            // 
            this.itemsList.AutoSize = true;
            this.itemsList.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.itemsList.Location = new System.Drawing.Point(60, 46);
            this.itemsList.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.itemsList.Name = "itemsList";
            this.itemsList.Size = new System.Drawing.Size(103, 20);
            this.itemsList.TabIndex = 1;
            this.itemsList.Text = "Store Items";
            // 
            // itemsListBox
            // 
            this.itemsListBox.FormattingEnabled = true;
            this.itemsListBox.ItemHeight = 20;
            this.itemsListBox.Location = new System.Drawing.Point(60, 71);
            this.itemsListBox.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.itemsListBox.Name = "itemsListBox";
            this.itemsListBox.Size = new System.Drawing.Size(178, 144);
            this.itemsListBox.TabIndex = 2;
            // 
            // shoppingCartListBox
            // 
            this.shoppingCartListBox.FormattingEnabled = true;
            this.shoppingCartListBox.ItemHeight = 20;
            this.shoppingCartListBox.Location = new System.Drawing.Point(522, 71);
            this.shoppingCartListBox.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.shoppingCartListBox.Name = "shoppingCartListBox";
            this.shoppingCartListBox.Size = new System.Drawing.Size(178, 144);
            this.shoppingCartListBox.TabIndex = 4;
            // 
            // shoppingCartListBoxLabel
            // 
            this.shoppingCartListBoxLabel.AutoSize = true;
            this.shoppingCartListBoxLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.shoppingCartListBoxLabel.Location = new System.Drawing.Point(522, 46);
            this.shoppingCartListBoxLabel.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.shoppingCartListBoxLabel.Name = "shoppingCartListBoxLabel";
            this.shoppingCartListBoxLabel.Size = new System.Drawing.Size(124, 20);
            this.shoppingCartListBoxLabel.TabIndex = 3;
            this.shoppingCartListBoxLabel.Text = "Shopping Cart";
            // 
            // addToCart
            // 
            this.addToCart.Location = new System.Drawing.Point(314, 126);
            this.addToCart.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.addToCart.Name = "addToCart";
            this.addToCart.Size = new System.Drawing.Size(127, 35);
            this.addToCart.TabIndex = 5;
            this.addToCart.Text = "Add To Cart ->";
            this.addToCart.UseVisualStyleBackColor = true;
            this.addToCart.Click += new System.EventHandler(this.addToCart_Click);
            // 
            // makePurchase
            // 
            this.makePurchase.Location = new System.Drawing.Point(590, 226);
            this.makePurchase.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.makePurchase.Name = "makePurchase";
            this.makePurchase.Size = new System.Drawing.Size(112, 35);
            this.makePurchase.TabIndex = 6;
            this.makePurchase.Text = "Purchase";
            this.makePurchase.UseVisualStyleBackColor = true;
            this.makePurchase.Click += new System.EventHandler(this.makePurchase_Click);
            // 
            // vendorListBox
            // 
            this.vendorListBox.FormattingEnabled = true;
            this.vendorListBox.ItemHeight = 20;
            this.vendorListBox.Location = new System.Drawing.Point(60, 287);
            this.vendorListBox.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.vendorListBox.Name = "vendorListBox";
            this.vendorListBox.Size = new System.Drawing.Size(178, 144);
            this.vendorListBox.TabIndex = 8;
            // 
            // vendorListBoxLabel
            // 
            this.vendorListBoxLabel.AutoSize = true;
            this.vendorListBoxLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.vendorListBoxLabel.Location = new System.Drawing.Point(60, 262);
            this.vendorListBoxLabel.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.vendorListBoxLabel.Name = "vendorListBoxLabel";
            this.vendorListBoxLabel.Size = new System.Drawing.Size(76, 20);
            this.vendorListBoxLabel.TabIndex = 7;
            this.vendorListBoxLabel.Text = "Vendors";
            // 
            // storeProfitLabel
            // 
            this.storeProfitLabel.AutoSize = true;
            this.storeProfitLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.storeProfitLabel.Location = new System.Drawing.Point(487, 262);
            this.storeProfitLabel.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.storeProfitLabel.Name = "storeProfitLabel";
            this.storeProfitLabel.Size = new System.Drawing.Size(101, 20);
            this.storeProfitLabel.TabIndex = 9;
            this.storeProfitLabel.Text = "Store Profit";
            // 
            // storeProfitValue
            // 
            this.storeProfitValue.AutoSize = true;
            this.storeProfitValue.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.storeProfitValue.Location = new System.Drawing.Point(599, 262);
            this.storeProfitValue.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.storeProfitValue.Name = "storeProfitValue";
            this.storeProfitValue.Size = new System.Drawing.Size(54, 20);
            this.storeProfitValue.TabIndex = 10;
            this.storeProfitValue.Text = "$0.00";
            // 
            // ConsignementShop
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(818, 458);
            this.Controls.Add(this.storeProfitValue);
            this.Controls.Add(this.storeProfitLabel);
            this.Controls.Add(this.vendorListBox);
            this.Controls.Add(this.vendorListBoxLabel);
            this.Controls.Add(this.makePurchase);
            this.Controls.Add(this.addToCart);
            this.Controls.Add(this.shoppingCartListBox);
            this.Controls.Add(this.shoppingCartListBoxLabel);
            this.Controls.Add(this.itemsListBox);
            this.Controls.Add(this.itemsList);
            this.Controls.Add(this.headerText);
            this.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.Name = "ConsignementShop";
            this.Text = "Consignement Shop Demo";
            this.Load += new System.EventHandler(this.ConsignementShop_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label headerText;
        private System.Windows.Forms.Label itemsList;
        private System.Windows.Forms.ListBox itemsListBox;
        private System.Windows.Forms.ListBox shoppingCartListBox;
        private System.Windows.Forms.Label shoppingCartListBoxLabel;
        private System.Windows.Forms.Button addToCart;
        private System.Windows.Forms.Button makePurchase;
        private System.Windows.Forms.ListBox vendorListBox;
        private System.Windows.Forms.Label vendorListBoxLabel;
        private System.Windows.Forms.Label storeProfitLabel;
        private System.Windows.Forms.Label storeProfitValue;
    }
}

