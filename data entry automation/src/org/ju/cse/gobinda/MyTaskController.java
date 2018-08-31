package org.ju.cse.gobinda;

import java.awt.Color;
import java.util.ArrayList;

public class MyTaskController implements Runnable {

	private ArrayList<String> messages;

	public MyTaskController(ArrayList<String> messages) {
		this.messages = messages;
	}

	@Override
	public void run() {

		int px, py;
		boolean pageLoaded;

		KeyBoardMouse.getInstance().wait(1000);

		for (String message : messages) {

			// clearing previous muse id
			px = -400;
			py = 101;
			KeyBoardMouse.getInstance().mouseMove(px, py);
			KeyBoardMouse.getInstance().wait(500);
			KeyBoardMouse.getInstance().mouseClick();
			KeyBoardMouse.getInstance().wait(500);
			KeyBoardMouse.getInstance().clearAllText();
			KeyBoardMouse.getInstance().wait(500);

			
			// writing new muse id
			KeyBoardMouse.getInstance().writeMessage(message);
			KeyBoardMouse.getInstance().wait(500);
			KeyBoardMouse.getInstance().pressEnter();
			KeyBoardMouse.getInstance().wait(500);
			
			
			KeyBoardMouse.getInstance().wait(1000);

			// waiting for page loading
			px = -114;
			py = 671;

			boolean ansButtonPopUP = false, alreadySolved = false;
			while (ansButtonPopUP == false && alreadySolved == false) {
				for(int i=-297,j=319;i<=-257;i++){
					Color pixelColor2 = KeyBoardMouse.getInstance().getPixelColor(i,j);
					if ((pixelColor2.getBlue() == 255 && pixelColor2.getGreen() == 255 && pixelColor2.getRed() == 255) == false) {
						alreadySolved = true;
						//System.out.println("found solved for "+String.valueOf(i)+" "+String.valueOf(j));
						//System.out.println(String.format("%d %d %d\n", pixelColor2.getRed(), pixelColor2.getGreen(), pixelColor2.getBlue()));
						break;
					}
				}

				Color pixelColor = KeyBoardMouse.getInstance().getPixelColor(px, py);
				if ((pixelColor.getBlue() == 255 && pixelColor.getGreen() == 255 && pixelColor.getRed() == 255) == false) {
					ansButtonPopUP = true;
				}
				KeyBoardMouse.getInstance().wait(2000);
			}
			
			KeyBoardMouse.getInstance().wait(500);
			if (alreadySolved) {
				DataEntryTask.appendOutput(message.concat(" already updated!"));
				continue;
			}

			// move mouse avove answer button
			KeyBoardMouse.getInstance().mouseMove(px, py);
			KeyBoardMouse.getInstance().wait(500);
			KeyBoardMouse.getInstance().mouseClick();
			KeyBoardMouse.getInstance().wait(500);

			
			
			pageLoaded = false;
			KeyBoardMouse.getInstance().wait(500);
			while (pageLoaded == false) {
				for(int i=-891,j=227;i<=-754;i++){
					Color pixelColor2 = KeyBoardMouse.getInstance().getPixelColor(i,j);
					if (pixelColor2.getBlue() < 248 && pixelColor2.getGreen() < 248 && pixelColor2.getRed() < 248) {
						pageLoaded = true;
						break;
					}
				}
				KeyBoardMouse.getInstance().wait(2000);
			}
			
			//give aditional 1 second
			KeyBoardMouse.getInstance().wait(500);
			KeyBoardMouse.getInstance().wait(1000);

			// pressing upon modify button
			px = -678;
			py = 116;
			KeyBoardMouse.getInstance().mouseMove(px, py);
			KeyBoardMouse.getInstance().wait(500);
			KeyBoardMouse.getInstance().mouseClick();
			KeyBoardMouse.getInstance().wait(500);

			// waiting for list loading
			px = -1017;
			py = 450;
			pageLoaded = false;
			KeyBoardMouse.getInstance().wait(500);
			while (pageLoaded == false) {
				Color pixelColor = KeyBoardMouse.getInstance().getPixelColor(px, py);
				if (pixelColor.getBlue() == 255 && pixelColor.getGreen() == 255 && pixelColor.getRed() == 255) {
					
				} else {
					pageLoaded = true;
				}
				KeyBoardMouse.getInstance().wait(2000);
			}


			//give 1 second to load list
			KeyBoardMouse.getInstance().wait(1000);
			
			// scroll down work
			px = -350;
			py = 543;
			KeyBoardMouse.getInstance().wait(500);
			KeyBoardMouse.getInstance().mouseMove(px, py);
			KeyBoardMouse.getInstance().wait(500);
			KeyBoardMouse.getInstance().mouseClick();
			KeyBoardMouse.getInstance().wait(500);

			
			// select gdpr from the list
			px = -966;
			py = 537;
			KeyBoardMouse.getInstance().wait(500);
			KeyBoardMouse.getInstance().mouseMove(px, py);
			KeyBoardMouse.getInstance().wait(500);
			KeyBoardMouse.getInstance().mouseClick();
			KeyBoardMouse.getInstance().wait(500);

			
			// select user textfiled
			px = -524;
			py = 364;
			KeyBoardMouse.getInstance().wait(500);
			KeyBoardMouse.getInstance().mouseMove(px, py);
			KeyBoardMouse.getInstance().wait(500);
			KeyBoardMouse.getInstance().mouseClick();
			KeyBoardMouse.getInstance().wait(500);

			
			// writting no data in the text field
			KeyBoardMouse.getInstance().writeMessage("no data");
			KeyBoardMouse.getInstance().wait(500);

			// select submit
			px = -676;
			py = 699;
			KeyBoardMouse.getInstance().mouseMove(px, py);
			KeyBoardMouse.getInstance().wait(500);
			KeyBoardMouse.getInstance().mouseClick();
			KeyBoardMouse.getInstance().wait(500);

			// select okay
			px = -716;
			py = 435;
			KeyBoardMouse.getInstance().mouseMove(px, py);
			KeyBoardMouse.getInstance().wait(500);
			KeyBoardMouse.getInstance().mouseClick();
			KeyBoardMouse.getInstance().wait(500);

			DataEntryTask.appendOutput(message + " done");
			
			
			pageLoaded = false;
			while (pageLoaded == false) {
				for(int i=-297,j=319;i<=-257;i++){
					Color pixelColor2 = KeyBoardMouse.getInstance().getPixelColor(i,j);
					if ((pixelColor2.getBlue() == 255 && pixelColor2.getGreen() == 255 && pixelColor2.getRed() == 255) == false) {
						pageLoaded = true;
						break;
					}
				}
				KeyBoardMouse.getInstance().wait(2000);
			}
			KeyBoardMouse.getInstance().wait(2000);
		}
	}

}
