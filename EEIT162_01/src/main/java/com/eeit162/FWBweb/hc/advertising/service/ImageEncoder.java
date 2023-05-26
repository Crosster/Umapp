package com.eeit162.FWBweb.hc.advertising.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

public class ImageEncoder {
	private static final int BUFFER_SIZE = 8192; // 8KB

	public String encodeImage(byte[] imageBytes) throws IOException {
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		OutputStream base64Output = Base64.getEncoder().wrap(byteOutput);

		try (InputStream input = new ByteArrayInputStream(imageBytes)) {
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead;
			while ((bytesRead = input.read(buffer)) != -1) {
				base64Output.write(buffer, 0, bytesRead);
			}
		}

		base64Output.close(); // This also closes byteOutput
		return byteOutput.toString("UTF-8");
	}
}
