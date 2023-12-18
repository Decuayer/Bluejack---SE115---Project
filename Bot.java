public class Bot {
	public int botReturn(int player, int cpu, Cards[] cpuHand,Cards[] cpuTable){
		// 0 -> Stand
		// 1,2,3,4 -> Special Cards
		// 5 -> All Blue
		// 6 -> Draw Card
		int[] arrNumber = new int[cpuHand.length];
		String[] arrColour = new String[cpuHand.length];
		int cpuToGame = 20 - cpu;
		int colourCounter = 0;
		
		for(int i = 0; i < cpuTable.length; i++) {
			if(!cpuTable[i].getColour().equals("0")) {
				colourCounter++;
			}
		}
		int[] arrTableNumber = new int[colourCounter];
		for(int i = 0; i < arrTableNumber.length; i++) {
			if(cpuTable[i].getNumber().equals("2x")) {
				arrTableNumber[i] = 0;
			}else if(cpuTable[i].getNumber().equals("+/-")) {
				arrTableNumber[i] = 0;
			}else {
				arrTableNumber[i] = Integer.parseInt(cpuTable[i].getNumber());
			}
		}
		for(int i = 0; i < cpuHand.length ; i++) {
			if(arrTableNumber.length == 0) {
				arrNumber[i] = 0;
			} else {
				if(cpuHand[i].getNumber().equals("2x")) {
					arrNumber[i] = 2*arrTableNumber[arrTableNumber.length-1];
				}else if (cpuHand[i].getNumber().equals("+/-")) {
					arrNumber[i] = -arrTableNumber[arrTableNumber.length-1];
				}else {
					arrNumber[i] = Integer.parseInt(cpuHand[i].getNumber());
				}
			}
			arrColour[i] = cpuHand[i].getColour();
		}
		
		String[] arrTableColour = new String[colourCounter];
		boolean allBlue = false;
		int allBlueCounter = 0;
		for(int i = 0; i < arrTableColour.length; i++) {
			arrTableColour[i] = cpuTable[i].getColour();
		}
		for(int i = 0; i < arrTableColour.length; i++) {
			if(arrTableColour[i].equals("Blue")) {
				allBlueCounter++;
			}
		}
		if(allBlueCounter == arrTableColour.length) {
			allBlue = true;
		}
		for(int i = 0; i < arrNumber.length; i++) {
			if(cpuToGame == arrNumber[i]) {
				if(cpuToGame == 0) {
					break;
				}
				return i+1;
			}
		}
		
		if(cpuToGame == 0) {
			if(allBlue) {
				return 5;
			}
			return 0;
		} else if(cpu > 15 && cpu < 20) {
			if(cpu > player) {
				return 0;
			}else {
				for(int i = 0; i < arrNumber.length; i++) {
					if(arrNumber[i] < cpuToGame && arrNumber[i] > 0) {
						return i+1;
					}
				}
				return 0;
			}
		} else if(cpu > 20) {
			for(int i = 0; i < arrNumber.length; i++) {
				if(arrNumber[i] < 0) {
					return i+1;
				}
			}
			return 0;
		} else {
			return 6;
		}
	}
}