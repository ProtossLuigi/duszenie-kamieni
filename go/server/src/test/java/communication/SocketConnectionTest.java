package communication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SocketConnectionTest {

    SocketConnection socketConnection;

    @Before
    public void setUp() throws Exception {
        socketConnection = new SocketConnection();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setupAccess() {
        assertTrue(socketConnection.setupAccess(0));
        assertFalse(socketConnection.setupAccess(-1));
    }

    @Test(expected = RuntimeException.class)
    public void listen() throws IOException {
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        Socket mockSocket = mock(Socket.class);
        when(mockSocket.getInputStream()).thenThrow(new IOException());
        when(mockServerSocket.accept()).thenReturn(mockSocket).thenThrow(new IOException()).thenThrow(new RuntimeException());
        socketConnection.setSocket(mockServerSocket);
        socketConnection.listen();
    }
}