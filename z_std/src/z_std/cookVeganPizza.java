package z_std;

public class cookVeganPizza {

	public static void main(String[] args) {
		
			try {
				cookVeganPizza();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	
	
	public static void cookVeganPizza() throws InterruptedException {
	    Base base = new Base();
	    Tomatoes tomatoes = new Tomatoes();
	    Tofu tofu = new Tofu();
	    Bake bake = new Bake();
	    
	    java.util.List<Thread> stepOfCook = new java.util.ArrayList<>();
	    
	    stepOfCook.add(base);
	    stepOfCook.add(tomatoes);
	    stepOfCook.add(tofu);
	    stepOfCook.add(bake);
	    
	    
//	    stepOfCook.get(0).start();
//	    	stepOfCook.get(0).join();
//	    
//	    stepOfCook.get(1).start();
//	    	stepOfCook.get(1).join();
//	    
//	    stepOfCook.get(2).start();
//	    	stepOfCook.get(2).join();
	    
	    for (Thread step : stepOfCook) {
	        step.start();
	        step.join();
	        
	    }
	    
	}
	
	
	
	
	

	
}







class Base extends Thread {    
    @Override
    public void run() {
        System.out.println("cook base");
    }
}
 
class Tomatoes extends Thread {
    @Override
    public void run() {
        for (int i = 3; i >= 1; i--) {
            System.out.println("slice tomatoes " + i);
        }
    }
}
 
class Tofu extends Thread {
    @Override
    public void run() {
        System.out.println("fry tofu");
    }
}
class Bake extends Thread{
    @Override
    public void run() {
        for (int i = 4; i >= 0; i--) {
            if (i == 0) {
                System.out.println("Your pizza is ready!");
                break;
            }
            System.out.println("to bake..." + i + " min");
 
        }
    }
}