/*
 |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
 | Author: Dominik Dancs                                                           |
 | Website: http://dodancs.moow.info                                               |
 | E-Mail: dodancs@moow.info                                                       |
 |                                                                                 |
 | Support me on Patreon: https://www.patreon.com/dodancs                          |
 |                                                                                 |
 | Copyright: 2015 Dominik Dancs & FenixPortal.eu | All rights reserved.           |
 |                                                                                 |
 | License: GNU                                                                    |
 |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author dodan_000
 */
public class GUI_Launcher extends JFrame {

    //Main frame
    private JFrame gui = new JFrame(); //create JFrame gui
    
    //Settings
    private final String GUI_icon_file = "/Resources/icon.png"; //gui icon file
    private final String GUI_logo_file = "/Resources/logo.png"; //gui logo file
    private final int GUI_width = 430; //gui width
    private final int GUI_height = 500; //gui height
    private final String GUI_title = "FenixLauncher " + Launcher.launcherVersion; //gui title
    
    /**
     * Creates new form GUI_Launcher
     */
    public GUI_Launcher() {
        gui = new JFrame(); //create new JFrame gui
    }
    
    public void init() {
        //initComponents();
        
        //set look and feel
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());} 
        catch (Exception e) {Launcher.console.error(e.toString());e.printStackTrace();}
        
        guiComponents(); //init gui components
        
        gui.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); //set default close operation

        gui.setSize(GUI_width, GUI_height); //set gui width & height
        gui.setResizable(false); //disable resizing
        gui.setLocationRelativeTo(null); //center the gui
        gui.setTitle(GUI_title); //set gui title
        gui.setIconImage(Launcher.filehelper.getImage(GUI_icon_file)); //set gui icon
        
        gui.addWindowListener(new WindowAdapter() { //add windos closing operation listener
            @Override
            public void windowClosing(WindowEvent we) {
                Launcher.console.message("End of log here..." + System.getProperty("line.separator")); //close log
            }
        });
        
        //Generated GUI elements
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(gui.getContentPane());
        gui.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        
        pack();
        
    }
    
    public void guiComponents() { //initialize all gui components
        
        //create gui components
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); //set logo aligment
        jLabel1.setIcon(Launcher.filehelper.getIcon(GUI_logo_file)); //load logo file
        
        jButton1.setText("Nastavenia"); //settings button text
        jButton1.addActionListener(new ActionListener() { //settings button action listener
        @Override
        public void actionPerformed(ActionEvent e) {
            Launcher.gui_settings.getSettingsValues(); //get current settings values
            Launcher.gui_settings.show(); //show Settings GUI
        }});
        
        if (Launcher.loggedIn) {jLabel2.setText("Prihlásený ako: "+Launcher.currentUser);} //current user label text
        else {jLabel2.setText("Nie ste prihlásený");}

        jButton2.setText("Účty"); //accounts button text
        
        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Zvoľte modpack, ktorý chcete spustiť"); //modpack selection label text

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(Launcher.modpacksList.toArray())); //modpacks avaliable dropdown
        jComboBox1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                javax.swing.JComboBox combo = (javax.swing.JComboBox)e.getSource();
                Launcher.currentModpack = (String)combo.getSelectedItem();
                Launcher.console.info("Current modpack: " + Launcher.currentModpack);
                if (!Launcher.modpacks.checkFor(Launcher.currentModpack)) {
                    jButton4.setText("Stiahnúť");
                    jLabel5.setText("Modpack nie je stiahnutý");
                    ActionListener[] listenersToRemove = jButton4.getActionListeners();
                    for (ActionListener l : listenersToRemove) {
                        jButton4.removeActionListener(l);
                    }
                    jButton4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Launcher.gui_downloader.show(); //show Downloader GUI
                        
                        //Download modpack
                        JSONObject o = Launcher.modpacks.getOnlineModpackInfo(Launcher.currentModpack);
                        JSONObject c = (JSONObject)JSONValue.parse(Launcher.jsonhelper.oGetKeyArrayObjects(o, "versions").get(0));

                        String localName = Launcher.downloadsDir + Launcher.slash + Launcher.jsonhelper.oGetKeyValueString(o,"code_name") + "_" + Launcher.jsonhelper.oGetKeyValueString(o,"current_version") + ".zip";
                        String downloadUrl = Launcher.jsonhelper.oGetKeyValueString(c,Launcher.jsonhelper.oGetKeyValueString(o,"current_version")+"|"+Launcher.jsonhelper.oGetKeyValueString(o,"mc_version"));

                        Launcher.downloader.quickDownload(downloadUrl, localName, Launcher.modpacksDir + Launcher.slash + Launcher.jsonhelper.oGetKeyValueString(o,"code_name") + "_" + Launcher.jsonhelper.oGetKeyValueString(o,"current_version"), Launcher.currentModpack);
                        
                    }});
                }
                if (Launcher.modpacks.checkFor(Launcher.currentModpack)) {
                    jButton4.setText("Upraviť");
                    jLabel5.setText("Modpack je pripravený na spustenie");
                    ActionListener[] listenersToRemove = jButton4.getActionListeners();
                    for (ActionListener l : listenersToRemove) {
                        jButton4.removeActionListener(l);
                    }
                    jButton4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Launcher.gui_modpackEdit.show(); //show ModpackEdit GUI
                    }});
                }
                
                jLabel4.setIcon(getModpackIcon()); //currently selected modpack thumbnail
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText(""); //currently selected modpack status text

        jButton3.setText("Spustiť"); //launch button text
        
        jButton4.setText("Upraviť"); //modpacks button text
    }
    
    public void updateModpacks(String[] modpacks) {
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(modpacks));
        Launcher.currentModpack = (String)jComboBox1.getSelectedItem();
        if (!Launcher.modpacks.checkFor(Launcher.currentModpack)) {
            jButton4.setText("Stiahnúť");
            jLabel5.setText("Modpack nie je stiahnutý");
            ActionListener[] listenersToRemove = jButton4.getActionListeners();
            for (ActionListener l : listenersToRemove) {
                jButton4.removeActionListener(l);
            }
            jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Launcher.gui_downloader.show(); //show Downloader
                
                //Download modpack
                JSONObject o = Launcher.modpacks.getOnlineModpackInfo(Launcher.currentModpack);
                JSONObject c = (JSONObject)JSONValue.parse(Launcher.jsonhelper.oGetKeyArrayObjects(o, "versions").get(0));
                
                String localName = Launcher.downloadsDir + Launcher.slash + Launcher.jsonhelper.oGetKeyValueString(o,"code_name") + "_" + Launcher.jsonhelper.oGetKeyValueString(o,"current_version") + ".zip";
                String downloadUrl = Launcher.jsonhelper.oGetKeyValueString(c,Launcher.jsonhelper.oGetKeyValueString(o,"current_version")+"|"+Launcher.jsonhelper.oGetKeyValueString(o,"mc_version"));
                
                Launcher.downloader.quickDownload(downloadUrl, localName, Launcher.modpacksDir + Launcher.slash + Launcher.jsonhelper.oGetKeyValueString(o,"code_name") + "_" + Launcher.jsonhelper.oGetKeyValueString(o,"current_version"), Launcher.currentModpack);
                
            }});
        }
        if (Launcher.modpacks.checkFor(Launcher.currentModpack)) {
            jButton4.setText("Upraviť");
            jLabel5.setText("Modpack je pripravený na spustenie");
            ActionListener[] listenersToRemove = jButton4.getActionListeners();
            for (ActionListener l : listenersToRemove) {
                jButton4.removeActionListener(l);
            }
            jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Launcher.gui_modpackEdit.show(); //show ModpackEdit GUI
            }});
        }
        
        jLabel4.setIcon(getModpackIcon()); //currently selected modpack thumbnail
    }
    
    public Icon getModpackIcon() {
        if (!Launcher.modpacks.checkFor(Launcher.currentModpack)) {
            JSONObject o = Launcher.modpacks.getOnlineModpackInfo(Launcher.currentModpack);
            String modpackIconFilename = "";
            modpackIconFilename = Launcher.jsonhelper.oGetKeyValueString(o,"code_name") + "_" + Launcher.jsonhelper.oGetKeyValueString(o,"current_version") + ".png";
            if (!Launcher.filehelper.fileExists(Launcher.downloadsDir + Launcher.slash + modpackIconFilename)) {Launcher.filehelper.downloadFile(Launcher.modpackImgUrl + modpackIconFilename, Launcher.downloadsDir + Launcher.slash + modpackIconFilename);}
            return new ImageIcon(Launcher.downloadsDir + Launcher.slash + modpackIconFilename);
        }
        else {
            JSONObject o = Launcher.modpacks.getModpackInfo(Launcher.currentModpack);
            String modpackIconFilename = "";
            modpackIconFilename = Launcher.jsonhelper.oGetKeyValueString(o,"code_name") + "_" + Launcher.jsonhelper.oGetKeyValueString(o,"version") + ".png";
            if (!Launcher.filehelper.fileExists(Launcher.downloadsDir + Launcher.slash + modpackIconFilename)) {Launcher.filehelper.downloadFile(Launcher.modpackImgUrl + modpackIconFilename, Launcher.downloadsDir + Launcher.slash + modpackIconFilename);}
            return new ImageIcon(Launcher.downloadsDir + Launcher.slash + modpackIconFilename);
        }
    }
    
    @Override
    public void show() {gui.setVisible(true);} //show gui
    @Override
    public void hide() {gui.setVisible(false);} //hide gui
    public void close() {gui.dispose();} //close gui
    @Override
    public void disable() {gui.setEnabled(false);} //disable gui
    @Override
    public void enable() {gui.setEnabled(true);} //enable gui

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.darkGray);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");

        jButton1.setText("Nastavenia");

        jLabel2.setText("Prihlásený ako: Sonic656");

        jButton2.setText("Účty");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Zvoľte modpack, ktorý chcete spustiť");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "GeniusPack 1.0", "GeniusLite 1.0" }));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Modpack je pripravený na spustenie");
        jLabel5.setToolTipText("");

        jButton3.setText("Spustiť");

        jButton4.setText("Modpacky");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
