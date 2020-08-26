package A2;

public class Warehouse{

	protected Shelf[] storage;
	protected int nbShelves;
	public Box toShip;
	public UrgentBox toShipUrgently;
	static String problem = "problem encountered while performing the operation";
	static String noProblem = "operation was successfully carried out";
	
	public Warehouse(int n, int[] heights, int[] lengths){
		this.nbShelves = n;
		this.storage = new Shelf[n];
		for (int i = 0; i < n; i++){
			this.storage[i]= new Shelf(heights[i], lengths[i]);
		}
		this.toShip = null;
		this.toShipUrgently = null;
	}
	
	public String printShipping(){
		Box b = toShip;
		String result = "not urgent : ";
		while(b != null){
			result += b.id + ", ";
			b = b.next;
		}
		result += "\n" + "should be already gone : ";
		b = toShipUrgently;
		while(b != null){
			result += b.id + ", ";
			b = b.next;
		}
		result += "\n";
		return result;
	}
	
 	public String print(){
 		String result = "";
		for (int i = 0; i < nbShelves; i++){
			result += i + "-th shelf " + storage[i].print();
		}
		return result;
	}
	
 	public void clear(){
 		toShip = null;
 		toShipUrgently = null;
 		for (int i = 0; i < nbShelves ; i++){
 			storage[i].clear();
 		}
 	}
 	
 	/**
 	 * initiate the merge sort algorithm
 	 */
	public void sort(){
		mergeSort(0, nbShelves -1);
	}
	
	/**
	 * performs the induction step of the merge sort algorithm
	 * @param start
	 * @param end
	 */
	protected void mergeSort(int start, int end){
		if(start<end){
			int middle = (start+end)/2;
			mergeSort(start,middle);
			mergeSort(middle+1,end);
			merge(start,middle,end);
		}
	}
	
	/**
	 * performs the merge part of the merge sort algorithm
	 * @param start
	 * @param mid
	 * @param end
	 */
	protected void merge(int start, int mid, int end){
		//ADD YOUR CODE HERE
		//Size of the two sub-arrays:
		int n1 = mid-start+1;
		int n2 = end-mid;

		//Create the two sub-arrays:
		Shelf[] L = new Shelf[n1];
		Shelf[] R = new Shelf[n2];

		//Initialize both sub-arrays:
		for(int i=0; i<n1; i++) L[i] = storage[start+i];
		for(int j=0; j<n2; j++) R[j] = storage[mid+1+j];

		//Merge Process:
		int i=0, j=0; 	//indices of sub-arrays L and R
		int k = start;	//index of merged array

		while(i<n1 && j<n2){
			if(L[i].height <= R[j].height){
				storage[k] = L[i];
				i++;
			}else{
				storage[k] = R[j];
				j++;
			}
			k++;
		}

		//Check for remaining elements of L and R:
		while(i<n1){
			storage[k] = L[i];
			i++;
			k++;
		}
		while(j<n2){
			storage[k] = R[j];
			j++;
			k++;
		}
	}
	
	/**
	 * Adds a box is the smallest possible shelf where there is room available.
	 * Here we assume that there is at least one shelf (i.e. nbShelves >0)
	 * @param b
	 * @return problem or noProblem
	 */
	public String addBox (Box b){
		//ADD YOUR CODE HERE
		for(int i=0; i<storage.length; i++){
			if(storage[i].availableLength>=b.length && storage[i].height>=b.height){
				storage[i].addBox(b);
				return noProblem;
			}
		}
		return problem;
	}
	
	/**
	 * Adds a box to its corresponding shipping list and updates all the fields
	 * @param b
	 * @return problem or noProblem
	 */
	public String addToShip (Box b){
		//ADD YOUR CODE HERE
		if(b == null) return problem;
		if(b instanceof UrgentBox){
			if (toShipUrgently != null) {
				b.next = toShipUrgently;
				toShipUrgently.previous = b;
			}
			toShipUrgently = (UrgentBox) b;
		}else{
			if(toShip !=null){
				b.next = toShip;
				toShip.previous = b;
			}
			toShip = b;
		}
		return noProblem;
	}
	
	/**
	 * Find a box with the identifier (if it exists)
	 * Remove the box from its corresponding shelf
	 * Add it to its corresponding shipping list
	 * @param identifier
	 * @return problem or noProblem
	 */
	public String shipBox (String identifier){
		//ADD YOUR CODE HERE
		for(int i =0; i<nbShelves; i++){
			Box boxRemoved = storage[i].removeBox(identifier);
			if(boxRemoved!=null){
				addToShip(boxRemoved);
				return noProblem;
			}
		}
		return problem;
	}
	
	/**
	 * if there is a better shelf for the box, moves the box to the optimal shelf.
	 * If there are none, do not do anything
	 * @param b
	 * @param position
	 */
	public void moveOneBox (Box b, int position){
		//ADD YOUR CODE HERE
		for(int i =0; i<position; i++){
			Shelf candidateShelf = storage[i];
			if(candidateShelf.height>=b.height && candidateShelf.availableLength>=b.length){
				storage[position].removeBox(b.id);
				candidateShelf.addBox(b);
				break;
			}
		}
	}
	
	/**
	 * reorganize the entire warehouse : start with smaller shelves and first box on each shelf.
	 */
	public void reorganize (){
		//ADD YOUR CODE HERE
		for(int i =0; i<nbShelves; i++){
			Shelf shelfToOrganize = storage[i];

			int length=0;
			Box current = shelfToOrganize.firstBox;
			while(current!=null){
				length++;
				current = current.next;
			}

			Box[] boxes = new Box[length];
			current = shelfToOrganize.firstBox;
			int j =0;
			while(current!=null){
				boxes[j] = current;
				j++;
				current = current.next;
			}

			for(int x =0; x<length; x++){
				moveOneBox(boxes[x],i);
			}

		}
	}
}