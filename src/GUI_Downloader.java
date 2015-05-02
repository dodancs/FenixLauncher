import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author dodan_000
 */
public class GUI_Downloader extends JFrame {

    //Main frame
    private JFrame gui = new JFrame(); //create JFrame gui
    
    //Settings
    private final String GUI_icon_file = "/Resources/icon.png"; //gui icon file
    private final int GUI_width = 430; //gui width
    private final int GUI_height = 168; //gui height
    private final String GUI_title = "FenixLauncher - Downloader"; //gui title
    
    /**
     * Creates new form GUI_Launcher
     */
    public GUI_Downloader() {
        gui = new JFrame(); //create new JFrame gui
    }
    
    public void init() {
        //initComponents();
        
        //set look and feel
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());} 
        catch (Exception e) {Launcher.console.error(e.toString());e.printStackTrace();}
        
        guiComponents(); //init gui components
        
        gui.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); //set default close operation

        gui.setSize(GUI_width, GUI_height); //set gui width & height
        gui.setResizable(false); //disable resizing
        gui.setLocationRelativeTo(null); //center the gui
        gui.setTitle(GUI_title); //set gui title
        gui.setIconImage(Launcher.filehelper.getImage(GUI_icon_file)); //set gui icon
        
        gui.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                Launcher.gui_launcher.enable();
                Launcher.gui_console.enable();
                Launcher.downloader.stopDownload();
            }
        });
        
        //Generated GUI elements
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(gui.getContentPane());
        gui.getContentPane().setLayout(layout);
        //Layout here
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(0, 17, Short.MAX_VALUE))
        );
        pack();
        
    }
    
    public void guiComponents() { //initialize all gui components
        
        //create gui components
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sťahujem modpack: GENIUSPACK");

        jLabel2.setText("File info");
        jLabel2.setMaximumSize(new java.awt.Dimension(380, 14));
        jLabel2.setMinimumSize(new java.awt.Dimension(380, 14));
        jLabel2.setPreferredSize(new java.awt.Dimension(380, 14));

        jButton1.setText("Zrušiť");
        ActionListener[] listenersToRemove = jButton1.getActionListeners();
        for (ActionListener l : listenersToRemove) {
            jButton1.removeActionListener(l);
        }
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Launcher.downloader.stopDownload();
                jButton1.setText("Zavrieť");
                ActionListener[] listenersToRemove = jButton1.getActionListeners();
                for (ActionListener l : listenersToRemove) {
                    jButton1.removeActionListener(l);
                }
                jButton1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Launcher.gui_downloader.close();
            }});
        }});

    }
    
    public void setModpack(String newTitle) {
        jLabel1.setText("Sťahujem modpack: "+newTitle);
    }
    public void setFileInfo(String fileInfo) {
        jLabel2.setText(fileInfo);
    }
    public void setMaxProgress(long max) {
        jProgressBar1.setMaximum((int) max);
    }
    public void setProgress(long progress) {
        jProgressBar1.setValue((int) progress);
    }
    
    @Override
    public void show() {gui.setVisible(true);Launcher.gui_launcher.disable();Launcher.gui_console.disable();} //show gui
    @Override
    public void hide() {gui.setVisible(false);Launcher.gui_launcher.enable();Launcher.gui_console.enable();} //hide gui
    public void close() {gui.dispose();Launcher.gui_launcher.enable();Launcher.gui_console.enable();reload();} //close gui
    @Override
    public void disable() {gui.setEnabled(false);} //disable gui
    @Override
    public void enable() {gui.setEnabled(true);} //enable gui
    
    public void reload() {
        jButton1.setText("Zrušiť");
        ActionListener[] listenersToRemove = jButton1.getActionListeners();
        for (ActionListener l : listenersToRemove) {
            jButton1.removeActionListener(l);
        }
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Launcher.downloader.stopDownload();
                jButton1.setText("Zavrieť");
                ActionListener[] listenersToRemove = jButton1.getActionListeners();
                for (ActionListener l : listenersToRemove) {
                    jButton1.removeActionListener(l);
                }
                jButton1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Launcher.gui_downloader.close();
            }});
        }});
    }
    
    public void finished() {
        jButton1.setText("Zavrieť");
        ActionListener[] listenersToRemove = jButton1.getActionListeners();
        for (ActionListener l : listenersToRemove) {
            jButton1.removeActionListener(l);
        }
        jButton1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Launcher.gui_downloader.close();
        }});
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sťahujem modpack: GENIUSPACK");

        jLabel2.setText("File info");
        jLabel2.setMaximumSize(new java.awt.Dimension(380, 14));
        jLabel2.setMinimumSize(new java.awt.Dimension(380, 14));
        jLabel2.setPreferredSize(new java.awt.Dimension(380, 14));

        jButton1.setText("Zrušiť");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(0, 17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
