package secnumber;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.Timer;

@SuppressWarnings("serial")
class MiniSecnumber extends JFrame {

	private static final String STOP = "\u25A0";
	private static final String START = "\u25B6";
	private final ClockListener cl = new ClockListener();
	private final Timer timer = new Timer(1000, cl);
	private static final JTextField hourField = new JTextField(2);
	private static final JTextField minuteField = new JTextField(2);
	private static final JTextField secondField = new JTextField(2);

	public MiniSecnumber() {

		timer.setInitialDelay(0);

		JPanel panel = new JPanel();
		hourField.setHorizontalAlignment(JTextField.LEFT);
		hourField.setEditable(false);
		minuteField.setHorizontalAlignment(JTextField.CENTER);
		minuteField.setEditable(false);
		secondField.setHorizontalAlignment(JTextField.RIGHT);
		secondField.setEditable(false);
		hourField.setText("00");
		minuteField.setText("00");
		secondField.setText("00");
		panel.add(hourField);
		panel.add(minuteField);
		panel.add(secondField);

		final JToggleButton buttonGo = new JToggleButton(START);
		buttonGo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (buttonGo.isSelected()) {
					timer.start();
					buttonGo.setText(STOP);
				} else {
					timer.stop();
					buttonGo.setText(START);
				}
			}
		});
		panel.add(buttonGo);

		final JToggleButton buttonReset = new JToggleButton("C");
		buttonReset.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (buttonReset.isSelected()) {
					buttonGo.setText(START);
					buttonGo.setSelected(false);
					buttonReset.setSelected(false);
					setTime();
				}
			}
		});
		panel.add(buttonReset);

		this.add(panel);
		// this.setUndecorated(true); //Remove comments, if you need realy MINI-interface
						// Alt and arrow UP or DOWN for call menu to move window, then press Enter
						// Alt + F4 - for quick exit
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.pack();
		this.setVisible(true);
	}

	public void start() {
		timer.start();
	}

	public void setTime() {
		timer.stop();
		hourField.setText("00");
		minuteField.setText("00");
		secondField.setText("00");
		ClockListener.setSecondCount(0);
		ClockListener.setMinuteCount(0);
		ClockListener.setHourCount(0);
	}

	static class ClockListener implements ActionListener {

		private static int secondCount = 0;
		private static int minuteCount = 0;
		private static int hourCount = 0;
		String secondVolume;
		String minuteVolume;
		String hourVolume;

		public static void setSecondCount(int second) {
			secondCount = second;
		}

		public static void setMinuteCount(int minute) {
			minuteCount = minute;
		}

		public static void setHourCount(int hour) {
			hourCount = hour;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			secondCount++;
			if (secondCount == 60) {
				minuteCount++;
				secondCount = 0;
				secondVolume = "0" + secondCount;
			} else if (secondCount < 10) {
				secondVolume = "0" + secondCount;
			} else {
				secondVolume = "" + secondCount;
			}
			if (minuteCount == 60) {
				minuteCount = 0;
				hourCount++;
				minuteVolume = "0" + minuteCount;
			} else if (minuteCount < 10) {
				minuteVolume = "0" + minuteCount;
			} else {
				minuteVolume = "" + minuteCount;
			}
			if (hourCount == 24) {
				hourCount = 0;
				hourVolume = "0" + hourCount;
			} else if (hourCount < 10) {
				hourVolume = "0" + hourCount;
			} else {
				hourVolume = "" + hourCount;
			}
			secondField.setText(secondVolume);
			minuteField.setText(minuteVolume);
			hourField.setText(hourVolume);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				MiniSecnumber clock = new MiniSecnumber();
			}
		});
	}
}