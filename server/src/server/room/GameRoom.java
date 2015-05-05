package server.room;

import java.io.*;
import java.net.*;
import java.util.*;

import server.player.*;

public class GameRoom extends Thread {
	private ArrayList<Player> players;

	public GameRoom() {
		players = new ArrayList<Player>();
	}

	public void destroyGame(){
		for(int i = 0; i < players.size(); ++i){
			players.get(i).disconnect();
		}
		players = null;
	}
	
	public void add(Socket socket) throws IOException {
		synchronized (players) {
			if (players.size() < 5)
				players.add(new Player(socket));
		}
	}

	@Override
	public void run() {
		while (true) {
			for(int i = 0; i < players.size(); ++i)
				if(!players.get(i).isConnected())
					players.remove(i);
			
			for(int i = 0; i < players.size(); ++i){
				players.get(i).get();
				players.get(i).update();
			}
			for(int i = 0; i < players.size(); ++i){
				players.get(i).send(players);
			}
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
