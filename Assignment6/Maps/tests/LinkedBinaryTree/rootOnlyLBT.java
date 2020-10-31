import org.junit.*;
import cs2321.*;


@jug.SuiteName("root only - \"A\"")
public class rootOnlyLBT {

	private LinkedBinaryTree<String> TARGET = init();


	public LinkedBinaryTree<String> init() {
		return new LinkedBinaryTree<String>();
	}

	@Before
	public void setup() throws Throwable {
		TARGET.addRoot("A");
	}

	@org.junit.Test(timeout=10000)
	@jug.TestName("size: Size 1")
	public void Test1() throws Throwable {
		
		org.junit.Assert.assertEquals("size: Size 1", (Object)(1), (Object)(TARGET.size()));
	}

	@org.junit.Test()
	@jug.TestName("isEmpty: isEmpty is false")
	public void Test2() throws Throwable {
		
		org.junit.Assert.assertEquals("isEmpty: isEmpty is false", (Object)(false), (Object)(TARGET.isEmpty()));
	}

	@org.junit.Test()
	@jug.TestName("parent: parent if null")
	public void Test3() throws Throwable {
		
		org.junit.Assert.assertEquals("parent: parent if null", (Object)(null), (Object)(TARGET.parent(TARGET.root())));
	}

	@org.junit.Test()
	@jug.TestName("numChildren(): numChildren(root) is 0")
	public void Test4() throws Throwable {
		
		org.junit.Assert.assertEquals("numChildren(): numChildren(root) is 0", (Object)(0), (Object)(TARGET.numChildren(TARGET.root())));
	}

	@org.junit.Test()
	@jug.TestName("isInternal(): isInternal(root) is false")
	public void Test5() throws Throwable {
		
		org.junit.Assert.assertEquals("isInternal(): isInternal(root) is false", (Object)(false), (Object)(TARGET.isInternal(TARGET.root())));
	}

	@org.junit.Test()
	@jug.TestName("isExternal(): isExternal(root) is true")
	public void Test6() throws Throwable {
		
		org.junit.Assert.assertEquals("isExternal(): isExternal(root) is true", (Object)(true), (Object)(TARGET.isExternal(TARGET.root())));
	}

	@org.junit.Test()
	@jug.TestName("left(): left(root) is null")
	public void Test7() throws Throwable {
		
		org.junit.Assert.assertEquals("left(): left(root) is null", (Object)(null), (Object)(TARGET.left(TARGET.root())));
	}

	@org.junit.Test()
	@jug.TestName("right(): right(root) is null")
	public void Test8() throws Throwable {
		
		org.junit.Assert.assertEquals("right(): right(root) is null", (Object)(null), (Object)(TARGET.right(TARGET.root())));
	}

	@org.junit.Test()
	@jug.TestName("addLeft(): insertLeft(root,\"B\") returns \"B\"")
	public void Test9() throws Throwable {
		TARGET.addLeft(TARGET.root(), "B");
		
		org.junit.Assert.assertEquals("addLeft(): insertLeft(root,\"B\") returns \"B\"", (Object)("B"), (Object)(TARGET.left(TARGET.root()).getElement()));
	}

	@org.junit.Test()
	@jug.TestName("addRight(): insertRight(root,\"B\") returns \"B\"")
	public void Test10() throws Throwable {
		TARGET.addRight(TARGET.root(), "B");
		
		org.junit.Assert.assertEquals("addRight(): insertRight(root,\"B\") returns \"B\"", (Object)("B"), (Object)(TARGET.right(TARGET.root()).getElement()));
	}

}
