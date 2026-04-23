package ex02;
interface PhoneInterface {
	final int TIMEOUT = 10000;
	void sendCall();
	void receiveCall();
	default void printLogo() {
		System.out.println("** Phone **");
	}
}

class SamsungPhone implements PhoneInterface {
	@Override
	public void sendCall() {
		System.out.println("띵똥땅띵똥!");
	}
	
	@Override
	public void receiveCall() {
		System.out.println("빵상");
	}
	
	public void flash() {System.out.println("전화기에 불이 켜졌습니다.");}
}
public class InterfaceEx {
	public static void main(String[] args) {
		SamsungPhone phone = new SamsungPhone();
		phone.printLogo();
		phone.sendCall();
		phone.receiveCall();
		phone.flash();
	}
}
