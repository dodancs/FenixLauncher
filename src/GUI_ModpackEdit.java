import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author dodan_000
 */
public class GUI_ModpackEdit extends JFrame {

    //Main frame
    private JFrame gui = new JFrame(); //create JFrame gui
    
    //Settings
    private final String GUI_icon_file = "/Resources/icon.png"; //gui icon file
    private final int GUI_width = 430; //gui width
    private final int GUI_height = 500; //gui height
    private final String GUI_title = "FenixLauncher - Úprava modpacku"; //gui title
    
    /**
     * Creates new form GUI_Launcher
     */
    public GUI_ModpackEdit() {
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
            }
        });
        
        //Generated GUI elements
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(gui.getContentPane());
        gui.getContentPane().setLayout(layout);
        //Layout here
        
        pack();
        
    }
    
    public void guiComponents() { //initialize all gui components
        
        //create gui components
    }
    
    @Override
    public void show() {gui.setVisible(true);Launcher.gui_launcher.disable();Launcher.gui_console.disable();} //show gui
    @Override
    public void hide() {gui.setVisible(false);Launcher.gui_launcher.enable();Launcher.gui_console.enable();} //hide gui
    public void close() {gui.dispose();Launcher.gui_launcher.enable();Launcher.gui_console.enable();} //close gui
    @Override
    public void disable() {gui.setEnabled(false);} //disable gui
    @Override
    public void enable() {gui.setEnabled(true);} //enable gui
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
