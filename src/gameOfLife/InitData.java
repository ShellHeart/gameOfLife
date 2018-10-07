package gameOfLife;

public class InitData {
	private static int height;
	private static int width;
	private int duration;
	private int num=0;
	private static int[][] matrix;
	public InitData(int height,int width,int duration,int [][]matrix) {
		this.height=height;
		this.width=width;
		this.duration=duration;
		this.matrix=matrix;
	}
	public static void cellMatix() {
		int [][]nextMatrix=new int[height][width];
		for(int x=0;x<matrix.length;x++) {
			for(int y=0;y<matrix[0].length;y++) {
				nextMatrix[x][y]=0;
				int lifeNum=findLifeNum(x,y);
				if(matrix[x][y]==1) {
					if(lifeNum==2||lifeNum==3) {
						nextMatrix[x][y]=1;
					}
					else nextMatrix[x][y]=0;
				}
				else {
					if(lifeNum==3) {
						nextMatrix[x][y]=1;
					}
					else nextMatrix[x][y]=0;
				}
			}
		}
		matrix=nextMatrix;
	}
	public static int[][] getMatrix(){
		return matrix;
	}
	public static int getHeight() {
		return height;
	}
	public static int getWidth() {
		return width;
	}

	public static int findLifeNum(int x,int y) {
		int num=0;
		if(x!=0) {
			num+=matrix[x-1][y];
		}
		if(y!=0) {
			num+=matrix[x][y-1];
		}
		if(x!=0&&y!=0) {
			num+=matrix[x-1][y-1];
		}
		if(x!=width-1) {
			num+=matrix[x+1][y];
		}
		if(x!=width-1&&y!=0) {
			num+=matrix[x+1][y-1];
		}
		if(y!=height-1) {
			num+=matrix[x][y+1];
		}
		if(x!=0&&y!=height-1) {
			num+=matrix[x-1][y+1];
		}
		if(x!=width-1&&y!=height-1) {
			num+=matrix[x+1][y+1];
		}
		return num;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
