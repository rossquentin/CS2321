import org.junit.*;

import cs2321.*;
import net.datastructures.*;


@jug.SuiteName("Four Nodes")
public class fourNodesLBT {

	private LinkedBinaryTree<String> TARGET = init();


	public LinkedBinaryTree<String> init() {
		return new LinkedBinaryTree<String>();
	}

	@Before
	public void setup() throws Throwable {
		Position<String> n1 = TARGET.addRoot("A");
			Position<String> n2 = TARGET.addLeft(n1, "B");
			TARGET.addRight(n1, "C");
			TARGET.addLeft(n2, "D");
	}

	@org.junit.Test(timeout=10000)
	@jug.TestName("size() is 4")
	public void Test1() throws Throwable {
		
		org.junit.Assert.assertEquals("size() is 4", (Object)(4), (Object)(TARGET.size()));
	}

	@org.junit.Test()
	@jug.TestName("isInternal(root) is true")
	public void Test2() throws Throwable {
		
		org.junit.Assert.assertEquals("isInternal(root) is true", (Object)(true), (Object)(TARGET.isInternal(TARGET.root())));
	}

	@org.junit.Test()
	@jug.TestName("isInternal(root.right) is false")
	public void Test3() throws Throwable {
		
		org.junit.Assert.assertEquals("isInternal(root.right) is false", (Object)(false), (Object)(TARGET.isInternal(TARGET.right(TARGET.root()))));
	}

	@org.junit.Test()
	@jug.TestName("isInternal(root.left) is true")
	public void Test4() throws Throwable {
		
		org.junit.Assert.assertEquals("isInternal(root.left) is true", (Object)(true), (Object)(TARGET.isInternal(TARGET.left(TARGET.root()))));
	}

	@org.junit.Test()
	@jug.TestName("addLeft(root.right,\"E\")")
	public void Test5() throws Throwable {
		Position<String> n3 = TARGET.right(TARGET.root());
		Position<String> n5 = TARGET.addLeft(n3, "E");
		
		org.junit.Assert.assertEquals("addLeft(root.right,\"E\")", (Object)("E"), (Object)(TARGET.left(n3).getElement()));
	}

	@org.junit.Test()
	@jug.TestName("addLeft(root.left,\"E\") throws exception")
	public void Test6() throws Throwable {
		Position<String> n3 = TARGET.left(TARGET.root());
		
		{ boolean thrown = false;
			try {
				TARGET.addLeft(n3, "E");
			} catch (Throwable t) {
				thrown = true;
				org.junit.Assert.assertThat("addLeft(root.left,\"E\") throws exception", t, org.hamcrest.CoreMatchers.instanceOf(IllegalArgumentException.class));
			}
			if(!thrown){
				org.junit.Assert.fail("addLeft(root.left,\"E\") throws exception: Expected Throwable IllegalArgumentException");
			}
		}
	}

	@org.junit.Test()
	@jug.TestName("addRight(root.right,\"E\")")
	public void Test7() throws Throwable {
		Position<String> n3 = TARGET.right(TARGET.root());
		Position<String> n5 = TARGET.addRight(n3, "E");
		
		org.junit.Assert.assertEquals("addRight(root.right,\"E\")", (Object)("E"), (Object)(TARGET.right(n3).getElement()));
	}

	@org.junit.Test()
	@jug.TestName("addRight(root,\"E\") throws exception")
	public void Test8() throws Throwable {
		
		{ boolean thrown = false;
			try {
				TARGET.addRight(TARGET.root(), "E");
			} catch (Throwable t) {
				thrown = true;
				org.junit.Assert.assertThat("addRight(root,\"E\") throws exception", t, org.hamcrest.CoreMatchers.instanceOf(IllegalArgumentException.class));
			}
			if(!thrown){
				org.junit.Assert.fail("addRight(root,\"E\") throws exception: Expected Throwable IllegalArgumentException");
			}
		}
	}

	@org.junit.Test()
	@jug.TestName("parent(\"A\") is null")
	public void Test9() throws Throwable {
		
		org.junit.Assert.assertEquals("parent(\"A\") is null", (Object)(null), (Object)(TARGET.parent(TARGET.root())));
	}

	@org.junit.Test()
	@jug.TestName("parent(\"B\") is \"A\"")
	public void Test10() throws Throwable {
		
		org.junit.Assert.assertEquals("parent(\"B\") is \"A\"", (Object)(TARGET.root()), (Object)(TARGET.parent(TARGET.left(TARGET.root()))));
	}

	@org.junit.Test()
	@jug.TestName("parent(\"C\") is \"A\"")
	public void Test11() throws Throwable {
		
		org.junit.Assert.assertEquals("parent(\"C\") is \"A\"", (Object)(TARGET.root()), (Object)(TARGET.parent(TARGET.right(TARGET.root()))));
	}

	@org.junit.Test()
	@jug.TestName("parent(\"D\") is \"B\"")
	public void Test12() throws Throwable {
		
		org.junit.Assert.assertEquals("parent(\"D\") is \"B\"", (Object)("B"), (Object)(TARGET.parent(TARGET.left(TARGET.left(TARGET.root()))).getElement()));
	}

}
