package br.com.labs.order;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	public void create(){
		
		verifyQuantityOfItem();
	}
	
	private void verifyQuantityOfItem(){
		
	}
}
