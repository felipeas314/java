package br.com.labs.exception;

public class ItemQuantityUnavailable extends RuntimeException {

	public ItemQuantityUnavailable(String msg) {
		super(msg);
	}
}
