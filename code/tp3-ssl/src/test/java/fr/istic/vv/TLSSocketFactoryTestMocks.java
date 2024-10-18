package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TLSSocketFactoryTestMocks {

    // Test lorsque les protocoles supportés et activés sont nuls
    @Test
    public void preparedSocket_NullProtocols() {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket socket = Mockito.mock(SSLSocket.class);

        // Simule protocoles nuls
        when(socket.getSupportedProtocols()).thenReturn(null);
        when(socket.getEnabledProtocols()).thenReturn(null);
        
        factory.prepareSocket(socket);

        // Vérifie que setEnabledProtocols n'est jamais appelé
        verify(socket, never()).setEnabledProtocols(any(String[].class));
    }

    // Test mélange de protocoles supportés et activés
    @Test
    public void typical() {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket socket = Mockito.mock(SSLSocket.class);

        // Simule protocoles supportés et activés
        when(socket.getSupportedProtocols()).thenReturn(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"});
        when(socket.getEnabledProtocols()).thenReturn(new String[]{"SSLv3", "TLSv1"});

        factory.prepareSocket(socket);

        // Vérifie que les protocoles sont activés dans le bon ordre
        verify(socket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }

    // Test avec seulement des protocoles supportés fournis
    @Test
    public void supportedOnly() {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket socket = Mockito.mock(SSLSocket.class);

        // Simule seulement les protocoles supportés
        when(socket.getSupportedProtocols()).thenReturn(new String[]{"TLSv1.1", "TLSv1.2"});
        when(socket.getEnabledProtocols()).thenReturn(null);

        factory.prepareSocket(socket);

        // Vérifie que seuls les protocoles supportés sont activés
        verify(socket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1"});
    }

    // Test avec seulement des protocoles activés fournis
    @Test
    public void enabledOnly() {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket socket = Mockito.mock(SSLSocket.class);

        // Simule seulement les protocoles activés
        when(socket.getSupportedProtocols()).thenReturn(null);
        when(socket.getEnabledProtocols()).thenReturn(new String[]{"SSLv3", "TLSv1"});
        factory.prepareSocket(socket);

        // Vérifie que les protocoles activés sont bien utilisés
        verify(socket).setEnabledProtocols(new String[]{"SSLv3", "TLSv1"});
    }

    // Test avec aucun protocole supporté et activé
    @Test
    public void noProtocols() {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket socket = Mockito.mock(SSLSocket.class);

        // Simule pas de protocoles
        when(socket.getSupportedProtocols()).thenReturn(new String[]{});
        when(socket.getEnabledProtocols()).thenReturn(new String[]{});
        factory.prepareSocket(socket);

        // Vérifie que setEnabledProtocols n'est jamais appelé
        verify(socket, never()).setEnabledProtocols(any(String[].class));
    }

    // Test vérifier comportement avec valeurs arbitraires
    @Test
    public void arbitraryTest() {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket socket = Mockito.mock(SSLSocket.class);

        // Protocole supporté mais pas activé
        when(socket.getSupportedProtocols()).thenReturn(new String[]{"TLSv1.2", "TLSv1"});
        when(socket.getEnabledProtocols()).thenReturn(new String[]{"TLSv1"});

        factory.prepareSocket(socket);

        // Vérifie qu'il y a uniquement le protocole "TLSv1.2" qui est ajouté
        verify(socket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1"});
    }
}
