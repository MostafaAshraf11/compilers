package csen1002.tests.task1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task1.RegExToNfa;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task1TestsBatch8 {

	@Test
	public void testRegEx1() {
		RegExToNfa regExToNfa= new RegExToNfa("w;x;y#eywx...");
		assertEquals("0;1;3;5;7#w;x;y#0,e,1;1,y,3;3,w,5;5,x,7#0#7", regExToNfa.toString());
	}

	@Test
	public void testRegEx2() {
		RegExToNfa regExToNfa= new RegExToNfa("a;q;s;w#swq.|*a.");
		assertEquals("0;1;2;3;5;6;7;8;9;11#a;q;s;w#0,s,1;1,e,7;2,w,3;3,q,5;5,e,7;6,e,0;6,e,2;7,e,6;7,e,9;8,e,6;8,e,9;9,a,11#8#11", regExToNfa.toString());
	}

	@Test
	public void testRegEx3() {
		RegExToNfa regExToNfa= new RegExToNfa("c;f;o#f*cco.*.**.");
		assertEquals("0;1;2;3;4;5;6;7;9;11;12;13;15#c;f;o#0,f,1;1,e,0;1,e,3;2,e,0;2,e,3;3,e,12;3,e,15;4,c,5;5,e,6;5,e,11;6,c,7;7,o,9;9,e,6;9,e,11;11,e,4;11,e,13;12,e,4;12,e,13;13,e,12;13,e,15#2#15", regExToNfa.toString());
	}

	@Test
	public void testRegEx4() {
		RegExToNfa regExToNfa= new RegExToNfa("d;f;h#dh|he.*|*f.");
		assertEquals("0;1;2;3;4;5;6;7;9;10;11;12;13;14;15;17#d;f;h#0,d,1;1,e,5;2,h,3;3,e,5;4,e,0;4,e,2;5,e,13;6,h,7;7,e,9;9,e,6;9,e,11;10,e,6;10,e,11;11,e,13;12,e,4;12,e,10;13,e,12;13,e,15;14,e,12;14,e,15;15,f,17#14#17", regExToNfa.toString());
	}

	@Test
	public void testRegEx5() {
		RegExToNfa regExToNfa= new RegExToNfa("d;j;q#jde|.q|");
		assertEquals("0;1;2;3;4;5;7;8;9;10;11#d;j;q#0,j,1;1,e,2;1,e,4;2,d,3;3,e,7;4,e,5;5,e,7;7,e,11;8,q,9;9,e,11;10,e,0;10,e,8#10#11", regExToNfa.toString());
	}

	@Test
	public void testRegEx6() {
		RegExToNfa regExToNfa= new RegExToNfa("c;g;q#cg|q*|");
		assertEquals("0;1;2;3;4;5;6;7;8;9;10;11#c;g;q#0,c,1;1,e,5;2,g,3;3,e,5;4,e,0;4,e,2;5,e,11;6,q,7;7,e,6;7,e,9;8,e,6;8,e,9;9,e,11;10,e,4;10,e,8#10#11", regExToNfa.toString());
	}

	@Test
	public void testRegEx7() {
		RegExToNfa regExToNfa= new RegExToNfa("j;n;z#njz|z||j|");
		assertEquals("0;1;2;3;4;5;6;7;8;9;10;11;12;13;14;15;16;17#j;n;z#0,n,1;1,e,13;2,j,3;3,e,7;4,z,5;5,e,7;6,e,2;6,e,4;7,e,11;8,z,9;9,e,11;10,e,6;10,e,8;11,e,13;12,e,0;12,e,10;13,e,17;14,j,15;15,e,17;16,e,12;16,e,14#16#17", regExToNfa.toString());
	}

	@Test
	public void testRegEx8() {
		RegExToNfa regExToNfa= new RegExToNfa("a;c;o#ae.o|c.e.");
		assertEquals("0;1;3;4;5;6;7;9;11#a;c;o#0,a,1;1,e,3;3,e,7;4,o,5;5,e,7;6,e,0;6,e,4;7,c,9;9,e,11#6#11", regExToNfa.toString());
	}

	@Test
	public void testRegEx9() {
		RegExToNfa regExToNfa= new RegExToNfa("p;s;t;w;y#ys|wp*t|*||");
		assertEquals("0;1;2;3;4;5;6;7;8;9;10;11;12;13;14;15;16;17;18;19;20;21#p;s;t;w;y#0,y,1;1,e,5;2,s,3;3,e,5;4,e,0;4,e,2;5,e,21;6,w,7;7,e,19;8,p,9;9,e,8;9,e,11;10,e,8;10,e,11;11,e,15;12,t,13;13,e,15;14,e,10;14,e,12;15,e,14;15,e,17;16,e,14;16,e,17;17,e,19;18,e,6;18,e,16;19,e,21;20,e,4;20,e,18#20#21", regExToNfa.toString());
	}

	@Test
	public void testRegEx10() {
		RegExToNfa regExToNfa= new RegExToNfa("b;i;j;m;z#ji.m*.bz..");
		assertEquals("0;1;3;4;5;7;9;11#b;i;j;m;z#0,j,1;1,i,3;3,e,4;3,e,7;4,m,5;5,e,4;5,e,7;7,b,9;9,z,11#0#11", regExToNfa.toString());
	}

}