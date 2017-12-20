package com.acn.acit.watsonservices.texttospeech;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

public class TextToSpeechDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextToSpeech service = new TextToSpeech();
		service.setUsernameAndPassword("d67a1f09-1431-4675-a355-101e582bda7b", "0Nqmy2UEPczQ");

		try {
			String text = "Hello world";
			InputStream stream = service.synthesize(text, Voice.EN_ALLISON, AudioFormat.WAV).execute();
			InputStream in = WaveUtils.reWriteWaveHeader(stream);
			OutputStream out = new FileOutputStream("hello_world.wav");
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			out.close();
			in.close();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
