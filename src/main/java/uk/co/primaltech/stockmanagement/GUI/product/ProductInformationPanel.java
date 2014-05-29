package uk.co.primaltech.stockmanagement.GUI.product;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import uk.co.primaltech.stockmanagement.GUI.main.ContentPanel;
import uk.co.primaltech.stockmanagement.GUI.main.MainWindow;
import uk.co.primaltech.stockmanagement.Tuple;
import uk.co.primaltech.stockmanagement.product.Product;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class ProductInformationPanel extends ContentPanel {

    private List<Tuple<JLabel, JTextField>> productInfoList;

    private final Product product;

    public ProductInformationPanel(Product product) {
        super("Product information", false);
        assert product != null;
        this.product = product;

        initDefaults();
        createComponents();
    }

    private void initDefaults() {
        productInfoList = new ArrayList<>();
        
        Font boldFont = new Font("Tahoma", Font.BOLD, 11);

        /* Product name */
        JLabel productNameLabel = new JLabel("Product name: ");        
        productNameLabel.setFont(boldFont);
        productNameLabel.setMinimumSize(new Dimension(100, 20));
        productNameLabel.setPreferredSize(new Dimension(100, 20));
        productNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField productNameValue = new JTextField(product.getProductName());
        productNameValue.setEnabled(false);
        productNameValue.setPreferredSize(new Dimension(196, 20));
        productNameValue.setMinimumSize(new Dimension(6, 20));
        productInfoList.add(new Tuple<>(productNameLabel, productNameValue));

        /* Brand */
        JLabel brandLabel = new JLabel("Brand: ");
        brandLabel.setFont(boldFont);
        brandLabel.setMinimumSize(new Dimension(100, 20));
        brandLabel.setPreferredSize(new Dimension(100, 20));
        brandLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField brandValue = new JTextField();
        brandValue.setEnabled(false);
        brandValue.setPreferredSize(new Dimension(196, 20));
        brandValue.setMinimumSize(new Dimension(6, 20));
        productInfoList.add(new Tuple<>(brandLabel, brandValue));

        /* Serial */
        
        JLabel serialLabel = new JLabel("Serial: ");
        serialLabel.setFont(boldFont);
        serialLabel.setMinimumSize(new Dimension(100, 20));
        serialLabel.setPreferredSize(new Dimension(100, 20));
        serialLabel.setHorizontalAlignment(SwingConstants.RIGHT);        
        JTextField serialValue = new JTextField(product.getSerial());
        serialValue.setEnabled(false);
        serialValue.setPreferredSize(new Dimension(196, 20));
        serialValue.setMinimumSize(new Dimension(6, 20));
        productInfoList.add(new Tuple<>(serialLabel, serialValue));

        /* Supplier */
        JLabel supplierLabel = new JLabel("Supplier: ");
        supplierLabel.setFont(boldFont);
        supplierLabel.setMinimumSize(new Dimension(100, 20));
        supplierLabel.setPreferredSize(new Dimension(100, 20));
        supplierLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField supplierValue = new JTextField();
        supplierValue.setEnabled(false);
        supplierValue.setPreferredSize(new Dimension(196, 20));
        supplierValue.setMinimumSize(new Dimension(6, 20));
        productInfoList.add(new Tuple<>(supplierLabel, supplierValue));

        /* Price */
        JLabel priceLabel = new JLabel("Price: ");
        priceLabel.setFont(boldFont);
        priceLabel.setMinimumSize(new Dimension(100, 20));
        priceLabel.setPreferredSize(new Dimension(100, 20));        
        priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField priceValue = new JTextField(product.getPrice());
        priceValue.setEnabled(false);
        priceValue.setPreferredSize(new Dimension(196, 20));
        priceValue.setMinimumSize(new Dimension(6, 20));
        productInfoList.add(new Tuple<>(priceLabel, priceValue));

        /* DateIN */
        JLabel dateINLabel = new JLabel("Date IN: ");
        dateINLabel.setFont(boldFont);
        dateINLabel.setMinimumSize(new Dimension(100, 20));
        dateINLabel.setPreferredSize(new Dimension(100, 20));
        dateINLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField dateINValue = new JTextField();
        dateINValue.setEnabled(false);
        dateINValue.setPreferredSize(new Dimension(196, 20));
        dateINValue.setMinimumSize(new Dimension(6, 20));
        productInfoList.add(new Tuple<>(dateINLabel, dateINValue));

        /* DateOUT */
        JLabel dateOUTLabel = new JLabel("Date OUT: ");
        dateOUTLabel.setFont(boldFont);
        dateOUTLabel.setMinimumSize(new Dimension(100, 20));
        dateOUTLabel.setPreferredSize(new Dimension(100, 20));
        dateOUTLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField dateOUTValue = new JTextField();
        dateOUTValue.setEnabled(false);
        dateOUTValue.setPreferredSize(new Dimension(196, 20));
        dateOUTValue.setMinimumSize(new Dimension(6, 20));
        productInfoList.add(new Tuple<>(dateOUTLabel, dateOUTValue));

        /* Invoice */
        JLabel invoiceLabel = new JLabel("Invoice: ");
        invoiceLabel.setFont(boldFont);
        invoiceLabel.setMinimumSize(new Dimension(100, 20));
        invoiceLabel.setPreferredSize(new Dimension(100, 20));
        invoiceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField invoiceValue = new JTextField(product.getInvoice());
        invoiceValue.setEnabled(false);
        invoiceValue.setPreferredSize(new Dimension(196, 20));
        invoiceValue.setMinimumSize(new Dimension(6, 20));
        invoiceValue.setEnabled(false);
        productInfoList.add(new Tuple<>(invoiceLabel, invoiceValue));
    }

    private void createComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JPanel interiorPanel = new JPanel(new GridLayout(3, 3, 5, 10));

        int i = 1;
        for (Tuple<JLabel, JTextField> productInfoList1 : productInfoList) {
            JPanel flowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            flowPanel.add(productInfoList1.getX());

            flowPanel.add(productInfoList1.getY());

            interiorPanel.add(flowPanel);
        }
                               
        

        add(interiorPanel);

        add(Box.createVerticalStrut(10));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(Box.createVerticalStrut(20));

        JPanel buttonArea = new JPanel(new FlowLayout(FlowLayout.CENTER));

        URL editURL = MainWindow.class.getResource("/edit.png");
        ImageIcon editICON = new ImageIcon(Toolkit.getDefaultToolkit().getImage(editURL).getScaledInstance(30, 30, 100));
        JLabel editButton = new JLabel(editICON);
        editButton.setText("Edit Data");
        editButton.setHorizontalTextPosition(JLabel.CENTER);
        editButton.setVerticalTextPosition(JLabel.BOTTOM);
        buttonArea.add(editButton);

        JSeparator separator = new JSeparator(JSeparator.VERTICAL);
        separator.setPreferredSize(new Dimension(20, 40));
        buttonArea.add(Box.createHorizontalStrut(20));
        buttonArea.add(separator);

        URL soldURL = MainWindow.class.getResource("/sold.png");
        ImageIcon soldICON = new ImageIcon(Toolkit.getDefaultToolkit().getImage(soldURL).getScaledInstance(30, 30, 100));
        JLabel soldButton = new JLabel(soldICON);
        soldButton.setText("Sell Product");
        soldButton.setHorizontalTextPosition(JLabel.CENTER);
        soldButton.setVerticalTextPosition(JLabel.BOTTOM);
        buttonArea.add(soldButton);

        JSeparator separator2 = new JSeparator(JSeparator.VERTICAL);
        separator2.setPreferredSize(new Dimension(20, 40));
        buttonArea.add(Box.createHorizontalStrut(20));
        buttonArea.add(separator2);

        /* should only visible if the product was not yet sold */
        URL deleteURL = MainWindow.class.getResource("/delete.png");
        ImageIcon deleteICON = new ImageIcon(Toolkit.getDefaultToolkit().getImage(deleteURL).getScaledInstance(30, 30, 100));
        JLabel deleteButton = new JLabel(deleteICON);
        deleteButton.setText("Delete Product");
        deleteButton.setHorizontalTextPosition(JLabel.CENTER);
        deleteButton.setVerticalTextPosition(JLabel.BOTTOM);
        buttonArea.add(deleteButton);

        add(buttonArea);
    }
}
