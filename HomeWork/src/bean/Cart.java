package bean;

import java.util.ArrayList;
import java.util.Iterator;

public class Cart {
	private ArrayList<CartItem>cart;
	public Cart(){
		cart = new ArrayList<CartItem>();
	}
	
	//向购物车添加商品
	public void addCartItem(CartItem item){
		CartItem oldItem = null;
		if(item != null){
			for(int i = 0; i < cart.size(); i++){
				oldItem = cart.get(i);
				if(oldItem.getId().equals(item.getId())){
					oldItem.setQuantity(oldItem.getQuantity() + item.getQuantity());
					return;
				}
			}
			cart.add(item);
		}
	}
	
	//根据商品id修改商品数量
	public boolean updateCartItem(String id, int quantity){
		CartItem oldItem = null;
		for(int i = 0; i < cart.size(); i++){
			oldItem = cart.get(i);
			if(oldItem.getId().equals(id)){
				oldItem.setQuantity(quantity);
				return true;
			}
		}
		return false;
	}
	
	//根据id删除商品
	public boolean removeCartItem(String id){
		CartItem oldItem = null;
		for(int i = 0; i < cart.size(); i++){
			oldItem = cart.get(i);
			if(oldItem.getId().equals(id)){
				cart.remove(i);
				return true;
			}
		}
		return false;
	}
	
	
	//清空购物车
	public boolean clearCart(){
		cart.clear();
		return true;
	}
	
	//计算购物车中商品总额
	public double getTotalMoney(){
		Iterator<CartItem> it = cart.iterator();
		double sum = 0.0;
		CartItem item = null;
		while(it.hasNext()){
			item = it.next();
			sum += item.totalPrice();
		}
		return sum;
	}
	
	//计算购物车中商品总数量
	public int getTotalQuantity(){
		Iterator<CartItem> it = cart.iterator();
		int sum = 0;
		CartItem item = null;
		while(it.hasNext()){
			item = it.next();
			sum += item.getQuantity();
		}
		return sum;
	}
	
	//获得一个购物车对象
	public ArrayList<CartItem> getCart(){
		return this.cart;
	}
}
