package java8LambdaStream.Lambda;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Lambdas {
	public static void main(String ...args){

		// Filtering with lambdas	
		List<Shoe> inventory = Arrays.asList(
				new Shoe(40,"black","walking" ),
				new Shoe(42, "white","walking"),
				new Shoe(44, "black","sandals"),
				new Shoe(36, "green","sandals"));	

		//  create Constructor and constructor references  with Supplier 
		Supplier<Shoe>shee =  ()->new Shoe (45, "red","sandals"); // create objet 
		Shoe a12=shee.get(); //  e
		// [Shoe{color='green', size=36}]

		List<Shoe> greenShoes = filter(inventory, (Shoe a) -> "green".equals(a.getColor()));
		System.out.println("\nMy filter shoe by green color :");
		System.out.println(greenShoes);

		System.out.println("\nMy filter shoe by white color :");
		List<Shoe> whiteShoes=filter(inventory,(Shoe b)->"white".equals(b.getColor()));
		System.out.println(whiteShoes);

		System.out.println("\nMy sort all shoe  :");
		Comparator<Shoe> sh = (Shoe sh1, Shoe sh2) -> sh1.getColor().compareTo(sh2.getColor());
		//[Shoe{color='green', size=36}, Shoe{color='black', size=40}, Shoe{color='white', size=42}, Shoe{color='black', size=44}]
		inventory.sort(sh);
		System.out.println(inventory);

		// use forEach  with Consumer
		System.out.println("\nMy list only with forEach :");
		forEach(inventory,(Shoe b)->System.out.println(b));
		// Apply an action to each person in the list
		

		// Map each shoe with it size
		System.out.println("\nMy Map each shoe with it size :");
		List<Float> l = map(inventory, d -> d.getSize());
		System.out.println(l );
	}
	// filter with predicate 
	public static List<Shoe> filter(List<Shoe> inventory, ShoePredicate p){
		List<Shoe> result = new ArrayList<>();
		for(Shoe shoe : inventory){
			if(p.test(shoe)){
				result.add(shoe);
			}
		}
		return result;
	}   
	// use forEach, which takes a list of shoes and applies an operation on each  element of that list. In the following listing you use this forEach method combined with a
	//	lambda to print all the elements of the list
	public static <T> void forEach(List<T> list, Consumer<? super T> action) {		
		for(T shoe1 : list) {
			action.accept(shoe1);
		}
	}
	// Maps each shoe2 in a list to a value  
	public static <T, R> List<R> map(List<T> list, Function<? super T, R> mapper) {
		List<R> mappedList = new ArrayList<>();
		for(T shoe2 : list) {			
			mappedList.add(mapper.apply(shoe2));

		}
		return mappedList;
	}



	public static class Shoe {
		private float  size = 0;
		private String color = "";
		private String style =""; 

		public Shoe(float size, String color,String style){
			this.size = size;
			this.color =  color;
			this.style= style;

		} 

		public float getSize() {
			return size;
		}

		public void setSize(float  size) {
			this.size = size;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}
		public String getStyle() {
			return style;
		}

		public void setStyle(String style) {
			this.style = style;
		}

		@Override
		public String toString() {
			return "Shoe [size=" + size + ", color=" + color + ", style=" + style + "]";
		}

	}

	// alow you to Predicates are boolean-valued functions of one argument. The interface contains various default methods for composing predicates to complex logical terms (and, or, negate)
	@FunctionalInterface
	interface ShoePredicate{
		public boolean test(Shoe a);
	}

	// The   java.util.function.Consumer<T> interface  defines an abstract method named accept that  takes an object of generic type T and returns no result (void).
	@FunctionalInterface
	public interface Consumer<T>{
		void accept (T t) ;	
	}

	@FunctionalInterface
	public interface Fonction<T,R>{
		R apply (T t) ;	
	}
}
