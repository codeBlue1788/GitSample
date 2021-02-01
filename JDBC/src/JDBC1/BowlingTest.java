package JDBC1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BowlingTest {
	Game1 bg ;
	private void rollMany(int n, int pins) {
		for(int i = 0 ; i <n;i++) {
			bg.roll(pins);
		}
	}
	@Test
	public void testGutter() {
		rollMany(20,0);
		assertEquals(0, bg.score());
	}
	@Test
	public void testAllones() {
		rollMany(20,1);
		assertEquals(20, bg.score());
	}
	@Test
	public void testOneSpace() {
		rollSpace();
		bg.roll(3);
		rollMany(17,0);
		assertEquals(16, bg.score());
	}
	@Test
	public void testOneStrike() {
		rollStrike();
		bg.roll(3);
		bg.roll(4);
		rollMany(17, 0);
		assertEquals(24, bg.score());
	}
	@Test
	public void testPerfectGame() {
		rollMany(12,10);
		assertEquals(300, bg.score());
	}
	
	//每次測試之前執行此方法
	@BeforeEach
	public void before() {
		bg = new Game1();
	}
	//每次測試之後執行此方法
	@AfterEach
	public void after() {
		bg = null;
	}
	private void rollSpace() {
		bg.roll(5);
		bg.roll(5);
	}
	private void rollStrike() {
		bg.roll(10);
	}
	
}
class Game1{
	private int score = 0;
	private int[] rolls = new int[21];
	private int currentRoll = 0;
	public void roll(int pins) {
		score+=pins;
		rolls[currentRoll++] = pins;
	}
	
	public int score() {
		int score = 0;
		int frameIndex = 0;
		//局數
		for(int frame = 0; frame < 10; frame++) {
			if(isStike(frameIndex)) {
				score += 10 + strikeBonus(frameIndex);
				frameIndex++;
			}else if(isSpare(frameIndex)) {
				score += 10 + spareBonus(frameIndex);
				frameIndex+=2;
			}else {
				score += sumOfBallIndexFrame(frameIndex);
				frameIndex+=2;
			}
			
		} 
		return score;
	}
	private int sumOfBallIndexFrame(int frameIndex) {
		return rolls[frameIndex] + rolls[frameIndex+1];
	}
	private int spareBonus(int frameIndex) {
		return rolls[frameIndex+2];
	}
	private int strikeBonus(int frameIndex) {
		return rolls[frameIndex+1] + rolls[frameIndex+2];
	}
	private boolean isSpare(int frameIndex) {
		return rolls[frameIndex] + rolls[frameIndex +1] == 10;
	}
	private boolean isStike(int frameIndex) {
		return rolls[frameIndex] ==10;
	}
}
