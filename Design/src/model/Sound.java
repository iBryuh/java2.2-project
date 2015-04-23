package model;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	public static void sound(){
	try {
	    File soundFile = new File("a.wav"); //�������� ����
	    
	    //�������� AudioInputStream
	    //��� ��� ����� �������� IOException � UnsupportedAudioFileException
	    AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
	    
	    //�������� ���������� ���������� Clip
	    //����� �������� LineUnavailableException
	    Clip clip = AudioSystem.getClip();
	    
	    //��������� ��� �������� ����� � Clip
	    //����� �������� IOException � LineUnavailableException
	    clip.open(ais);
	    
	    clip.setFramePosition(0); //������������� ��������� �� �����
	    clip.start(); //�������!!!

	    //���� �� �������� ������ �������, �� ����� ���������, ���� ���� �� ����������
	        //� GUI-����������� ��������� 3 ������� �� �����������
	    Thread.sleep(clip.getMicrosecondLength()/1000);
	    clip.stop(); //�������������
	    clip.close(); //���������
	} catch(IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
	    exc.printStackTrace();
	} catch (InterruptedException exc) {}
	}

}
