package org.ju.cse.gobinda;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jnativehook.GlobalScreen;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseMotionListener;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
public class DataEntryTask extends JFrame implements NativeMouseMotionListener {

	private static DataEntryTask dataEntryTask;
	private static final long serialVersionUID = 1L;

	private CardLayout cardLayout;

	public static void initialize() {
		if (dataEntryTask != null) {
			return;
		}

		dataEntryTask = new DataEntryTask();
		dataEntryTask.setVisible(true);

		try {
			GlobalScreen.registerNativeHook();
			GlobalScreen.addNativeMouseMotionListener(dataEntryTask);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JPanel contentPane;
	private JPanel cardHolderPanel;
	
	private JTextArea inputTextArea;
	private JTextArea outputTextArea;
	
	private JTextField pointerDetailsTextField;

	public DataEntryTask() {
		super();

		cardLayout = new CardLayout();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 4, 0, 0));

		JButton btnShowInputPanel = new JButton("Input-Output");
		btnShowInputPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardHolderPanel, "inputOutputPanel");
			}
		});
		panel.add(btnShowInputPanel);

		


		JButton btnStartWorkingProcess = new JButton("Start");
		btnStartWorkingProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s[] = inputTextArea.getText().split("\n");
				ArrayList<String> arrList = new ArrayList<>(Arrays.asList(s));

				MyTaskController taskController = new MyTaskController(arrList);
				Thread thread = new Thread(taskController);
				thread.start();
			}
		});
		panel.add(btnStartWorkingProcess);

		pointerDetailsTextField = new JTextField();
		pointerDetailsTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ruleName = pointerDetailsTextField.getText();
				if (ruleName.trim().length() == 0) {
					return;
				}
			}
		});
		panel.add(pointerDetailsTextField);
		pointerDetailsTextField.setColumns(10);

		cardHolderPanel = new JPanel();
		contentPane.add(cardHolderPanel, BorderLayout.CENTER);
		cardHolderPanel.setLayout(cardLayout);

		JPanel inputOutputPanel = new JPanel();
		cardHolderPanel.add(inputOutputPanel, "inputOutputPanel");
		inputOutputPanel.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel inputPanel = new JPanel();
		inputOutputPanel.add(inputPanel);
		inputPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		inputPanel.add(scrollPane, BorderLayout.CENTER);

		inputTextArea = new JTextArea();
		scrollPane.setViewportView(inputTextArea);

		JLabel lblInputText = new JLabel("Write Input Text Here");
		lblInputText.setBorder(new LineBorder(Color.BLACK));
		lblInputText.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblInputText);

		JPanel outputPanel = new JPanel();
		inputOutputPanel.add(outputPanel);
		outputPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		outputPanel.add(scrollPane_1, BorderLayout.CENTER);

		outputTextArea = new JTextArea();
		scrollPane_1.setViewportView(outputTextArea);

		JLabel lblOutput = new JLabel("Output");
		lblOutput.setBorder(new LineBorder(Color.BLACK));
		lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_1.setColumnHeaderView(lblOutput);

	}

	public static void appendOutput(String message) {
		dataEntryTask.outputTextArea.append(message + "\n");
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent e) {
		Point p = e.getPoint();
		Color c = KeyBoardMouse.getInstance().getPixelColor(p.x, p.y);
		String value = String.format("[%d,%d] [%d,%d,%d]", p.x, p.y, c.getRed(), c.getGreen(), c.getBlue());
		dataEntryTask.pointerDetailsTextField.setText(value);
	}

}
