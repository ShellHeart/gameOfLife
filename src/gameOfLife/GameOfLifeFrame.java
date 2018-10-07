package gameOfLife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameOfLifeFrame extends JFrame implements ActionListener {
	private final JButton start;
	private final JButton pause;
	private final JLabel label;
	private final JTextField jtf;
	private boolean isStart = false;
	private final JTextField[][] tmatrix;
	private String duration;
	private int dur;
	private final JPanel showpl;
	private final int hg;
	private final int wid;
	private final int[][] mat;

	public GameOfLifeFrame(int height, int width, int[][] matix) {
		hg = height;
		wid = width;
		mat = matix;
		this.setSize(800, 600);
		this.setLocation(500, 200);
		this.setTitle("生命游戏");
		final JPanel buttonpl = new JPanel();
		start = new JButton("开始游戏");
		pause = new JButton("暂停游戏");
		label = new JLabel("                            动画间隔设置为（ms为单位）");
		jtf = new JTextField();
		showpl = new JPanel();
		showpl.setLayout(new GridLayout(height, width));
		tmatrix = new JTextField[height][width];
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				final JTextField text = new JTextField();
				tmatrix[x][y] = text;
				showpl.add(text);
			}
		}
		add("Center", showpl);
		getContentPane().add(buttonpl, BorderLayout.NORTH);
		buttonpl.setLayout(new GridLayout(2, 2));
		buttonpl.add(start);
		buttonpl.add(pause);
		buttonpl.add(label);
		buttonpl.add(jtf);
		buttonpl.setBackground(Color.WHITE);
		start.addActionListener(this);
		pause.addActionListener(this);
		jtf.addActionListener(this);
		this.setVisible(true);
		buttonpl.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public void showMatrix() {
		final int[][] matrix = InitData.getMatrix();
		for (int x = 0; x < matrix.length; x++) {
			for (int y = 0; y < matrix[0].length; y++) {
				if (matrix[x][y] == 1) {
					tmatrix[x][y].setBackground(Color.BLACK);
				}
				if (matrix[x][y] == 0) {
					tmatrix[x][y].setBackground(Color.WHITE);
				}
			}
		}
	}

	private void initShow() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "开始游戏") {
			duration = jtf.getText();
			dur = Integer.valueOf(duration);
			new InitData(hg, wid, dur, mat);
			isStart = true;
			new Thread(new GameOfLife()).start();
		}
		if (e.getActionCommand() == "暂停游戏") {
			isStart = false;
		}
	}

	private class GameOfLife implements Runnable {
		@Override
		public void run() {
			while (isStart) {
				InitData.cellMatix();
				showMatrix();
				try {
					TimeUnit.MILLISECONDS.sleep(dur);
				} catch (final InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
