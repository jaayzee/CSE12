import static org.junit.Assert.*;
import org.junit.Test;

public class CSE12Tester{
    @Test
        public void ctor_default(){
            CSE12 a00 = new CSE12();
            assertEquals(a00.getNum(), 0);
        }
    @Test
        public void ctor_2(){
            CSE12 c00 = new CSE12(406);
            assertEquals(c00.getNum(), 406);
        }
    @Test
        public void officeHour(){
            CSE12 b00 = new CSE12(202);
            assertEquals(b00.officeHour(), 202/10);
        }

}