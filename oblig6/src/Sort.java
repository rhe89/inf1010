/***
 * @author Roar Hoksnes Eriksen
 */

class Sort {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		new Start(args, startTime).initalize();
		long endTime = System.currentTimeMillis();
		System.out.println("NY TID: " + (endTime-startTime));
	}
}