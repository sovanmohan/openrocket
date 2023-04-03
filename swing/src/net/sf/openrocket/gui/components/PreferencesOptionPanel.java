package net.sf.openrocket.gui.components;

import net.miginfocom.swing.MigLayout;
import net.sf.openrocket.l10n.Translator;
import net.sf.openrocket.startup.Application;
import net.sf.openrocket.startup.Preferences;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * A panel that adds storage options for exporting preferences.
 */
public class PreferencesOptionPanel extends JPanel {
    private static final Translator trans = Application.getTranslator();
    private static final Preferences prefs = Application.getPreferences();

    public PreferencesOptionPanel() {
        super(new MigLayout("fill, ins 0"));

        JPanel panel = new JPanel(new MigLayout("fill, ins 4lp"));
        panel.setBorder(BorderFactory.createTitledBorder(trans.get("PreferencesOptionPanel.title")));

        // Export user directories
        JCheckBox exportUserDirectories = new JCheckBox(trans.get("PreferencesOptionPanel.checkbox.userDirectories"));
        exportUserDirectories.setToolTipText(trans.get("PreferencesOptionPanel.checkbox.userDirectories.ttip"));
        exportUserDirectories.setSelected(prefs.getExportUserDirectories());
        exportUserDirectories.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                prefs.setExportUserDirectories(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
        panel.add(exportUserDirectories, "wrap");

        // Export window information (position, size...)
        JCheckBox exportWindowInfo = new JCheckBox(trans.get("PreferencesOptionPanel.checkbox.windowInfo"));
        exportWindowInfo.setToolTipText(trans.get("PreferencesOptionPanel.checkbox.windowInfo.ttip"));
        exportWindowInfo.setSelected(prefs.getExportWindowInformation());
        exportWindowInfo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                prefs.setExportWindowInformation(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
        panel.add(exportWindowInfo, "wrap 10lp");


        this.add(panel, "growx, north");
    }
}
