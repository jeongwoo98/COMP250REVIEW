package A2;

public class Shelf {
	
	protected int height;
	protected int availableLength;
	protected int totalLength;
	protected Box firstBox;
	protected Box lastBox;

	public Shelf(int height, int totalLength){
		this.height = height;
		this.availableLength = totalLength;
		this.totalLength = totalLength;
		this.firstBox = null;
		this.lastBox = null;
	}
	
	protected void clear(){
		availableLength = totalLength;
		firstBox = null;
		lastBox = null;
	}
	
	public String print(){
		String result = "( " + height + " - " + availableLength + " ) : ";
		Box b = firstBox;
		while(b != null){
			result += b.id + ", ";
			b = b.next;
		}
		result += "\n";
		return result;
	}
	
	/**
	 * Adds a box on the shelf. Here we assume that the box fits in height and length on the shelf.
	 * @param b
	 */
	public void addBox(Box b){
		//ADD YOUR CODE HERE
		if(firstBox==null){
			firstBox =b;
			lastBox = firstBox;
		}else{
			lastBox.next = b;
			b.previous = lastBox;
			lastBox = lastBox.next;
		}
		availableLength -= b.length;
	}
	
	/**
	 * If the box with the identifier is on the shelf, remove the box from the shelf and return that box.
	 * If not, do not do anything to the Shelf and return null.
	 * @param identifier
	 * @return
	 */
	public Box removeBox(String identifier){
		//ADD YOUR CODE HERE
		Box currentBox = firstBox;
		while(currentBox!=null){
			if(currentBox.id.equals(identifier)){
				//Check if it is the head:
				if(currentBox==firstBox){
					if(currentBox.next!=null){
						firstBox = currentBox.next;
						firstBox.previous = null;
					}else{
						firstBox = null;
						lastBox = null;
					}
				}
				//Check if is the tail:
				else if(currentBox == lastBox){
					if(currentBox.previous !=null){
						lastBox = currentBox.previous;
						lastBox.next = null;
					}else{
						firstBox = null;
						lastBox = null;
					}
				}
				//Middle case: must carefully rearrange next and previous pointers
				else{
					currentBox.previous.next = currentBox.next;
					currentBox.next.previous = currentBox.previous;
				}
				//Remove the box and return the used space:
				currentBox.next = null;
				currentBox.previous = null;
				availableLength += currentBox.length;
				return currentBox;
			}
			currentBox = currentBox.next;
		}
		return null;
	}
}
