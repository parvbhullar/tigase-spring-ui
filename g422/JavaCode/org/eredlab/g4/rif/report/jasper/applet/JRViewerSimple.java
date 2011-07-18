package org.eredlab.g4.rif.report.jasper.applet;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;

/**
 * JRViewerSimple
 * 
* @author njdt
 * @since 2010-09-27
 */
public class JRViewerSimple extends JRViewer {

	protected JButton btnPlus = new javax.swing.JButton();

	public JRViewerSimple(JasperPrint jrPrint) throws JRException {
		super(jrPrint);

		tlbToolBar.remove(btnSave);
		tlbToolBar.remove(btnReload);

		btnPlus = new javax.swing.JButton();
		btnPlus.setToolTipText("关于此报表组件");
		btnPlus.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/org/eredlab/g4/rif/report/jasper/applet/image/about.gif")));
		btnPlus.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnPlus.setMaximumSize(new java.awt.Dimension(23, 23));
		btnPlus.setMinimumSize(new java.awt.Dimension(23, 23));
		btnPlus.setPreferredSize(new java.awt.Dimension(23, 23));
		btnPlus.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPlusActionPerformed(evt);
			}
		});
		tlbToolBar.add(btnPlus, 0);
	}

	protected void btnPlusActionPerformed(java.awt.event.ActionEvent evt) {
		JOptionPane.showMessageDialog(this, "此报表由信息系统集成与开发平台报表引擎(eRedG4.Report)强力驱动(BasedJasperReport)\n易道软件实验(中国.昆明)");
	}

}
