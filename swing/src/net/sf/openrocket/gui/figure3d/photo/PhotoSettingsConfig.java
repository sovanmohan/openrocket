package net.sf.openrocket.gui.figure3d.photo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.EventObject;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import net.sf.openrocket.gui.adaptors.BooleanModel;
import net.sf.openrocket.gui.adaptors.DoubleModel;
import net.sf.openrocket.gui.components.ColorIcon;
import net.sf.openrocket.gui.components.StyledLabel;
import net.sf.openrocket.gui.components.StyledLabel.Style;
import net.sf.openrocket.gui.components.UnitSelector;
import net.sf.openrocket.gui.figure3d.photo.sky.Sky;
import net.sf.openrocket.gui.figure3d.photo.sky.Sky.Credit;
import net.sf.openrocket.gui.figure3d.photo.sky.builtin.Lake;
import net.sf.openrocket.gui.figure3d.photo.sky.builtin.Meadow;
import net.sf.openrocket.gui.figure3d.photo.sky.builtin.Miramar;
import net.sf.openrocket.gui.figure3d.photo.sky.builtin.Mountains;
import net.sf.openrocket.gui.figure3d.photo.sky.builtin.Orbit;
import net.sf.openrocket.gui.figure3d.photo.sky.builtin.Storm;
import net.sf.openrocket.gui.util.ColorConversion;
import net.sf.openrocket.startup.Application;
import net.sf.openrocket.unit.UnitGroup;
import net.sf.openrocket.util.StateChangeListener;

@SuppressWarnings("serial")
public class PhotoSettingsConfig extends JTabbedPane {
	private static final JColorChooser colorChooser = new JColorChooser();
	
	private class ColorActionListener implements ActionListener {
		private final String valueName;
		private final Object o;
		
		ColorActionListener(final Object o, final String valueName) {
			this.valueName = valueName;
			this.o = o;
		}
		
		@Override
		public void actionPerformed(ActionEvent colorClickEvent) {
			try {
				final Method getMethod = o.getClass().getMethod("get" + valueName);
				final Method setMethod = o.getClass().getMethod("set" + valueName, net.sf.openrocket.util.Color.class);
				net.sf.openrocket.util.Color c = (net.sf.openrocket.util.Color) getMethod.invoke(o);
				Color awtColor = ColorConversion.toAwtColor(c);
				colorChooser.setColor(awtColor);
				JDialog d = JColorChooser.createDialog(PhotoSettingsConfig.this,
						"Color Chooser", true, colorChooser, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent okEvent) {
								Color selected = colorChooser.getColor();
								if (selected == null)
									return;
								try {
									setMethod.invoke(o, ColorConversion.fromAwtColor(selected));
								} catch (Throwable e1) {
									Application.getExceptionHandler().handleErrorCondition(e1);
								}
							}
						}, null);
				d.setVisible(true);
			} catch (Throwable e1) {
				Application.getExceptionHandler().handleErrorCondition(e1);
			}
		}
	}
	
	public PhotoSettingsConfig(final PhotoSettings p) {
		super();
		
		setPreferredSize(new Dimension(240, 320));
		
		final JButton sunLightColorButton = new JButton();
		sunLightColorButton.setMaximumSize(new Dimension(35, 25));
		
		final JButton skyColorButton = new JButton();
		skyColorButton.setMaximumSize(new Dimension(35, 25));
		
		final JButton smokeColorButton = new JButton();
		smokeColorButton.setMaximumSize(new Dimension(35, 25));
		
		final JButton flameColorButton = new JButton();
		flameColorButton.setMaximumSize(new Dimension(35, 25));
		
		p.addChangeListener(new StateChangeListener() {
			{
				stateChanged(null);
			}
			
			@Override
			public void stateChanged(EventObject e) {
				sunLightColorButton.setIcon(new ColorIcon(p.getSunlight()));
				skyColorButton.setIcon(new ColorIcon(p.getSkyColor()));
				smokeColorButton.setIcon(new ColorIcon(p.getSmokeColor()));
				flameColorButton.setIcon(new ColorIcon(p.getFlameColor()));
			}
		});
		sunLightColorButton.addActionListener(new ColorActionListener(p, "Sunlight"));
		skyColorButton.addActionListener(new ColorActionListener(p, "SkyColor"));
		smokeColorButton.addActionListener(new ColorActionListener(p, "SmokeColor"));
		flameColorButton.addActionListener(new ColorActionListener(p, "FlameColor"));
		
		
		
		addTab("Orientation", new JPanel(new MigLayout("fill")) {
			{
				add(new StyledLabel("Rocket", Style.BOLD));
				add(new JSeparator(SwingConstants.HORIZONTAL), "span, wrap, growx");
				
				add(new JLabel("Pitch"));
				DoubleModel pitchModel = new DoubleModel(p, "Pitch", UnitGroup.UNITS_ANGLE);
				add(new JSpinner(pitchModel.getSpinnerModel()), "w 40");
				add(new UnitSelector(pitchModel), "wrap");
				
				add(new JLabel("Yaw"));
				DoubleModel yawModel = new DoubleModel(p, "Yaw", UnitGroup.UNITS_ANGLE);
				add(new JSpinner(yawModel.getSpinnerModel()), "w 40");
				add(new UnitSelector(yawModel), "wrap");
				
				add(new JLabel("Roll"));
				DoubleModel rollModel = new DoubleModel(p, "Roll", UnitGroup.UNITS_ANGLE);
				add(new JSpinner(rollModel.getSpinnerModel()), "w 40");
				add(new UnitSelector(rollModel), "wrap");
				
				add(new JLabel("Advance"));
				DoubleModel advanceModel = new DoubleModel(p, "Advance", UnitGroup.UNITS_LENGTH);
				add(new JSpinner(advanceModel.getSpinnerModel()), "w 40");
				add(new UnitSelector(advanceModel), "wrap");
				
				add(new StyledLabel("Camera", Style.BOLD));
				add(new JSeparator(SwingConstants.HORIZONTAL), "span, wrap, growx");
				
				add(new JLabel("View Azimuth"));
				DoubleModel viewAzModel = new DoubleModel(p, "ViewAz", UnitGroup.UNITS_ANGLE);
				add(new JSpinner(viewAzModel.getSpinnerModel()), "w 40");
				add(new UnitSelector(viewAzModel), "wrap");
				
				add(new JLabel("View Altitude"));
				DoubleModel viewAltModle = new DoubleModel(p, "ViewAlt", UnitGroup.UNITS_ANGLE);
				add(new JSpinner(viewAltModle.getSpinnerModel()), "w 40");
				add(new UnitSelector(viewAltModle), "wrap");
				
				add(new JLabel("View Distance"));
				DoubleModel viewDistanceModel = new DoubleModel(p, "ViewDistance", UnitGroup.UNITS_LENGTH);
				add(new JSpinner(viewDistanceModel.getSpinnerModel()), "w 40");
				add(new UnitSelector(viewDistanceModel), "wrap");
				
				add(new JLabel("FoV"));
				DoubleModel fovModel = new DoubleModel(p, "Fov", UnitGroup.UNITS_ANGLE);
				add(new JSpinner(fovModel.getSpinnerModel()), "w 40");
				add(new UnitSelector(fovModel), "wrap");
			}
		});
		
		addTab("Environment", new JPanel(new MigLayout("fill")) {
			{
				add(new StyledLabel("Light", Style.BOLD));
				add(new JSeparator(SwingConstants.HORIZONTAL), "span, wrap, growx");
				
				add(new JLabel("Sun Light"));
				add(sunLightColorButton, "wrap");
				
				add(new JLabel("Ambiance"));
				DoubleModel ambianceModel = new DoubleModel(p, "Ambiance", 100, UnitGroup.UNITS_NONE, 0, 100);
				add(new JSpinner(ambianceModel.getSpinnerModel()), "wrap");
				
				add(new JLabel("Light Azimuth"));
				DoubleModel lightAzModel = new DoubleModel(p, "LightAz", UnitGroup.UNITS_ANGLE);
				add(new JSpinner(lightAzModel.getSpinnerModel()), "w 40");
				add(new UnitSelector(lightAzModel), "wrap");
				
				add(new JLabel("Light Altitude"));
				DoubleModel lightAltModle = new DoubleModel(p, "LightAlt", UnitGroup.UNITS_ANGLE);
				add(new JSpinner(lightAltModle.getSpinnerModel()), "wrap");
				
				add(new StyledLabel("Sky", Style.BOLD));
				add(new JSeparator(SwingConstants.HORIZONTAL), "span, wrap, growx");
				
				add(new JLabel("Sky Color"));
				add(skyColorButton, "wrap");
				
				add(new JLabel("Sky Image"));
				
				add(new JComboBox(new DefaultComboBoxModel(new Object[] {
						null,
						Mountains.instance,
						Meadow.instance,
						Storm.instance,
						Lake.instance,
						Orbit.instance,
						Miramar.instance
				}) {
				}) {
					{
						addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								Object s = ((JComboBox) e.getSource()).getSelectedItem();
								if (s instanceof Sky) {
									p.setSky((Sky) s);
									skyColorButton.setEnabled(false);
								} else if (s == null) {
									p.setSky(null);
									skyColorButton.setEnabled(true);
								}
							}
						});
						
						setSelectedItem(p.getSky());
					}
				}, "wrap");
				
				final JLabel creditLabel = new JLabel("Image Credit:");
				add(creditLabel, "wrap");
				
				final JTextArea credit = new JTextArea();
				credit.setEditable(false);
				credit.setCursor(null);
				credit.setOpaque(false);
				credit.setFocusable(false);
				credit.setFont(creditLabel.getFont());
				add(credit, "span, gap left 10px");
				
				final StateChangeListener skyChange = new StateChangeListener() {
					@Override
					public void stateChanged(EventObject e) {
						if (p.getSky() instanceof Sky.Credit) {
							credit.setText(((Credit) p.getSky()).getCredit());
						} else {
							credit.setText("");
						}
					}
				};
				p.addChangeListener(skyChange);
				
				skyChange.stateChanged(null);
				
			}
		});
		
		addTab("Effects", new JPanel(new MigLayout("fill")) {
			{
				add(new StyledLabel("Smoke & Flame", Style.BOLD));
				add(new JSeparator(SwingConstants.HORIZONTAL), "span, wrap, growx");
				
				add(new JLabel("Smoke"));
				BooleanModel smokeModel = new BooleanModel(p, "Smoke");
				add(new JCheckBox(smokeModel), "split 2, w 15");
				
				add(smokeColorButton, "wrap");
				smokeModel.addEnableComponent(smokeColorButton);
				
				add(new JLabel("Smoke Opacity"));
				DoubleModel smokeAlphaModel = new DoubleModel(p, "SmokeAlpha", 100, UnitGroup.UNITS_NONE, 0, 100);
				JSpinner opacitySpinner = new JSpinner(smokeAlphaModel.getSpinnerModel());
				add(opacitySpinner, "wrap");
				smokeModel.addEnableComponent(opacitySpinner);
				
				add(new JLabel("Flame"));
				BooleanModel fireModel = new BooleanModel(p, "Flame");
				add(new JCheckBox(fireModel), "split 2, w 15");
				
				add(flameColorButton, "wrap");
				fireModel.addEnableComponent(flameColorButton);
				
				add(new JLabel("Flame Aspect Ratio"));
				DoubleModel flameAspectModel = new DoubleModel(p, "FlameAspectRatio", 100, UnitGroup.UNITS_NONE, 25, 250);
				JSpinner flameAspectSpinner = new JSpinner(flameAspectModel.getSpinnerModel());
				add(flameAspectSpinner, "wrap");
				fireModel.addEnableComponent(flameAspectSpinner);
				
				add(new JLabel("Sparks"));
				JCheckBox sparksCheck = new JCheckBox(new BooleanModel(p, "Sparks"));
				add(sparksCheck, "wrap");
				fireModel.addEnableComponent(sparksCheck);
				
				add(new JLabel("Exhaust Scale"));
				DoubleModel exhaustScaleModel = new DoubleModel(p, "ExhaustScale", 100, UnitGroup.UNITS_NONE, 0, 1000);
				add(new JSpinner(exhaustScaleModel.getSpinnerModel()), "wrap");
				
				add(new StyledLabel("Effects", Style.BOLD));
				add(new JSeparator(SwingConstants.HORIZONTAL), "span, wrap, growx");
				
				add(new JLabel("Speed"));
				add(new JCheckBox(new BooleanModel(p, "MotionBlurred")), "wrap");
			}
		});
		
	}
}
