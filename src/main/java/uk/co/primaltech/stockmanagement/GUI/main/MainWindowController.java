package uk.co.primaltech.stockmanagement.GUI.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Stack;
import javax.swing.SwingUtilities;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class MainWindowController implements Runnable {
        
    private static volatile MainWindowController instance = null;

    /* Instance of MainWindow. */
    private MainWindow mainWindow;

    /* Thread control. */
    private static boolean terminate;
    private static final Object lock = new Object();

    /* Object containing information for the update. */
    private static Stack<Object> updateObject;
    private static Stack<MessageType> messageType;

    public enum MessageType {
    }

    public static MainWindowController getInstance() {
        /* If there isn't an instance yet, create one. */
        if (instance == null) {
            instance = new MainWindowController();
            new Thread(instance).start();

            /* Wait until initialization has been completed. */
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
        }
        return instance;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("MainWindowController Thread");

        initialize();

        /* Warn that initialization has been made. */
        synchronized (lock) {
            lock.notifyAll();
        }
        /* Main cycle. */
        while (!terminate) {
            synchronized (lock) {
                /* Nothing to do. Go sleep. */
                if (messageType.empty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        //interrupt? end...
                        terminate = true;
                        continue;
                    }
                }

                /* Get the next copy. */
                final MessageType tmpMsgType = messageType.pop();
                final Object tmpUpdateObj = updateObject.pop();

                /* Make a runnable for AWT to call. */
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        switch (tmpMsgType) {
                        }
                    }
                };
                SwingUtilities.invokeLater(r);
            }
        }
        
    }

    public void updateMainWindow(MessageType msgType, Object params) {
        synchronized (lock) {
            updateObject.push(params);
            messageType.push(msgType);
            lock.notifyAll();
        }
    }

    private void initialize() {
        /* Create JFrame. */
        mainWindow = new MainWindow();
                        
        messageType = new Stack<>();
        updateObject = new Stack<>();
        
        /* Add the main area to the main window. */
        mainWindow.getMainContent().add(TabManager.getInstance(), BorderLayout.CENTER);        
                        
        mainWindow.pack();
        mainWindow.setPreferredSize(new Dimension(1000, 500));
        mainWindow.setMinimumSize(new Dimension(1000, 500));
        mainWindow.setLocationRelativeTo(null);
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }
}