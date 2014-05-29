package uk.co.primaltech.stockmanagement.GUI.Search;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import uk.co.primaltech.stockmanagement.GUI.main.ContentPanel;
import uk.co.primaltech.stockmanagement.ProjectManager;
import uk.co.primaltech.stockmanagement.product.Product;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class ProductSearchPanel extends ContentPanel {

    private static volatile ProductSearchPanel instance = null;

    private final JTextField inputField = new JTextField();
    private final JButton searchButton = new JButton("Search");
    private final JProgressBar progressbar = new JProgressBar();
    private final JLabel searchingLabel = new JLabel();
    private final JRadioButton brandRadioB = new JRadioButton("Brand");
    private final JRadioButton serialRadioB = new JRadioButton("Serial");
    private final JRadioButton productNameRadioB = new JRadioButton("Product Name");

    public static ProductSearchPanel getInstance() {
        if (instance == null) {
            synchronized (ProductSearchPanel.class) {
                if (instance == null) {
                    instance = new ProductSearchPanel();
                }
            }
        }
        return instance;
    }

    private ProductSearchPanel() {
        super("Product Search", false);
        //set product name radio button as default
        productNameRadioB.setSelected(true);
        //create a btnGroup to only allow one selection
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(productNameRadioB);
        btnGroup.add(brandRadioB);
        btnGroup.add(serialRadioB);
        
        createComponents();
    }

    private void createComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(350, 110));
        setMinimumSize(new Dimension(350, 110));

        /* Indicative label. */
        JLabel label1 = new JLabel("Search:", SwingConstants.CENTER);
        label1.setAlignmentX(1.0f);


        /* Search fields. */
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel1.add(label1);
                

        inputField.selectAll();
        inputField.setHorizontalAlignment(JTextField.CENTER);
        ActionListener searchAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (inputField.getText().trim().isEmpty()) {
                    return;
                }
                initializeSearch(inputField.getText());
            }
        };
        
        inputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                searchButton.setEnabled(!inputField.getText().trim().isEmpty());
            }
        });
        inputField.setMaximumSize(new Dimension(450, 20));
                       
        searchButton.addActionListener(searchAction);
        searchButton.setEnabled(false);

//        panel1.add(Box.createHorizontalStrut(WIDTH))
        panel1.add(Box.createHorizontalStrut(10));
        panel1.add(inputField);
        panel1.add(Box.createHorizontalStrut(15));
        panel1.add(searchButton);

        this.add(Box.createVerticalStrut(12));
        this.add(panel1);
       
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel2.add(productNameRadioB);
        panel2.add(brandRadioB);        
        //        mtm1.setAlignmentX(0.0f);
        panel2.add(serialRadioB);
        
        this.add(panel2);
        this.add(Box.createHorizontalStrut(400));

        /* Progress bar. */
        //this.add(Box.createVerticalStrut(10));
        searchingLabel.setText("Looking for results in database...");
        searchingLabel.setVisible(false);
        searchingLabel.setAlignmentX(1.0f);
        progressbar.setVisible(false);
        progressbar.setIndeterminate(true);
        this.add(searchingLabel);
        this.add(progressbar);
    }
class MyMouseListener implements MouseListener {

        private final String option;

        public MyMouseListener(String option) {
            this.option = option;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (option == null || option.trim().isEmpty()) {
                return;
            }            
            initializeSearch(option);

        }

        @Override
        public void mousePressed(MouseEvent e) {
//            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
//            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
//            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseExited(MouseEvent e) {
//            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    
    private void initializeSearch(final String inputText) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                /* Disable buttons and show progress bar. */
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        inputField.setText(inputText);
                        inputField.setEnabled(false);
                        searchButton.setEnabled(false);
                        searchingLabel.setVisible(true);                        
                        progressbar.setVisible(true);                        
                    }
                });    
                
//                Product product = buscar a base de dados
//                if (product != null) {
//                    /* Add to project. */
                    Product p = new Product("a", "a","a", "a", null, null, "2", "aa");
                ProjectManager.getInstance().getSearchManager().newSearchTab(p);                    
//                }
                                                
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        /* Put components back to normal. */
                        inputField.setEnabled(true);
                        searchButton.setEnabled(true);
                        searchingLabel.setVisible(false);
                        progressbar.setVisible(false);
                    }
                });
            }
        });
        t.start();
    }   
}