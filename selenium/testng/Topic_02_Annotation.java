package testng;

import org.testng.annotations.*;

public class Topic_02_Annotation {
    @BeforeClass // phai co ham nao do rang Annotation vao
    public void beforeClass() {
        System.out.println("Before class");
    }

    @AfterClass // phai co ham nao do rang Annotation vao
    public void afterClass() {
        System.out.println("After class");
    }
    @BeforeGroups // phai co ham nao do rang Annotation vao
    public void beforeGroup() {
        System.out.println("Before Group");
    }

    @AfterGroups // phai co ham nao do rang Annotation vao
    public void afterGroup() {
        System.out.println("after Group");
    }
    @BeforeMethod // phai co ham nao do rang Annotation vao
    public void beforeMethod() {
        System.out.println("before Mehthod");
    }
    @AfterMethod // phai co ham nao do rang Annotation vao
    public void afterMethod() {
        System.out.println("after Mehthod");
    }

    @BeforeSuite // phai co ham nao do rang Annotation vao
    public void beforeSuite() {
        System.out.println("before Suite");
    }
    @AfterSuite // phai co ham nao do rang Annotation vao
    public void afterSuite() {
        System.out.println("after Suite");
    }

    @BeforeTest // phai co ham nao do rang Annotation vao
    public void beforeTest() {
        System.out.println("before Test");
    }
    @AfterTest// phai co ham nao do rang Annotation vao
    public void afterTest() {
        System.out.println("after Test");
    }

    @Test
    public void Test_01() {
        System.out.println("Testcase 01");
    }
    @Test
    public void Test_02() {
        System.out.println("Testcase 02");
    }
    @Test
    public void Test_03() {
        System.out.println("Testcase 03");
    }



}
