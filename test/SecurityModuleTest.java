import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Hash Algorithm Tests")
public class SecurityModuleTest {

    @Test @Order(1)
    @DisplayName("SHA-256 length is 64")
    public void rsaHashLength() {
        assertEquals(64, new SHA256Hasher().hash("abc").length());
    }

    @Test @Order(2)
    @DisplayName("MD5 length is 32")
    public void md5HashLength() {
        assertEquals(32, new MD5Hasher().hash("abc").length());
    }

    @Test @Order(3)
    @DisplayName("Hashes differ")
    public void differentHashes() {
        assertNotEquals(new SHA256Hasher().hash("abc"), new MD5Hasher().hash("abc"));
    }
}