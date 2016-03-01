package bean;

import java.util.ArrayList;
import java.util.Iterator;

public class Cart {
	private ArrayList<CartItem>cart;
	public Cart(){
		cart = new ArrayList<CartItem>();
	}
	
	//���ﳵ�����Ʒ
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
	
	//������Ʒid�޸���Ʒ����
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
	
	//����idɾ����Ʒ
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
	
	
	//��չ��ﳵ
	public boolean clearCart(){
		cart.clear();
		return true;
	}
	
	//���㹺�ﳵ����Ʒ�ܶ�
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
	
	//���㹺�ﳵ����Ʒ������
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
	
	//���һ�����ﳵ����
	public ArrayList<CartItem> getCart(){
		return this.cart;
	}
}
