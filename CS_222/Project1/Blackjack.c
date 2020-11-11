#include <stdio.h>
#include <time.h>
#include <stdlib.h>

int playing(int);
int dealing();
void ending(int);
int beggining();

int gameRecord[4] = {0};
/* 0: times player won,
   1: times player lost,
   2: times player hit blackjack,
   3: times player busted */

FILE *fp;

int main() {
	srand(time(NULL));
	int pBank, action = 1;
	pBank = beggining();
	printf("Your balance is %d\n", pBank);
	while (action == 1) {
		pBank = playing(pBank);
		printf("Your new balance is %d\n", pBank);
		if (pBank < 10) {
			printf("Your balance is below the minimum bet requirement. Game over.\n");
			action = 0;
			break;
		}
		printf("Do you want to play again(1 = Yes, 2 = No)? ");
		scanf("%d", &action);
	}
	ending(pBank);
	return(0);
}

int beggining() {
	int pBank, i, t, j = 0;
	fp = fopen("game.txt", "r");
	if (fp) {
		fscanf (fp, "%d", &i);
		for (t = 0;t < 4;t++) {
			gameRecord[j] = i;
			fscanf (fp, "%d", &i);
			j++;
		}
		pBank = i;
		fclose (fp);
		if (pBank < 10) {
			printf("Previous game's balance too low. Starting new game.\n");
			for (t = 0;t < sizeof(gameRecord);t++) {
				gameRecord[t] = 0;
			}
			return(1000);
		}
		printf("Found save game. Continuing game.\n");
		return(pBank);
	} else {
		printf("Game not found. Starting new game.\n");
		for (t = 0;t < sizeof(gameRecord);t++) {
				gameRecord[t] = 0;
			}
		return(1000);
	}
}

void ending(int pBank) {
	fp = fopen("game.txt", "w");
	printf("Times player won: %d\n", gameRecord[0]);
	printf("Times player lost: %d\n", gameRecord[1]);
	printf("Times player hit blackjack: %d\n", gameRecord[2]);
	printf("Times player busted: %d\n", gameRecord[3]);
	printf("Current balance: %d\n", pBank);
	
	fprintf(fp, "%d\n", gameRecord[0]);
	fprintf(fp, "%d\n", gameRecord[1]);
	fprintf(fp, "%d\n", gameRecord[2]);
	fprintf(fp, "%d\n", gameRecord[3]);
	fprintf(fp, "%d\n", pBank);
	fclose (fp);
}

int dealing() {
	int card = rand() % 13 + 1;
	if (card == 1) return(11);
	else if (card <= 10) return (card);
	else return(10);
}

int playing(int pBank) {
	int i, bet, status = 1, temp, pHand = 0, cHand = 0, sCard, action;
	while (status == 1) {
		printf("Enter your bet: ");
		scanf("%d", &bet);
		
		if (!((bet >= 10) && (bet <= 1000) && (bet <= pBank))) {
			printf("Please enter a valid bet.\n");
			continue;
		}
		break;
		
	}
	for(i = 0;i < 2;i++){
		temp = dealing();
		printf("You got a(n) %d.\n", temp);
		pHand += temp;
	}
	for(i = 0;i < 1;i++){
		temp = dealing();
		printf("The dealer got a(n) %d.\n", temp);
		cHand += temp;
	}
	sCard = dealing();
	
	printf("You have a score of %d.\n", pHand);
	printf("The dealer has a score of %d and a hidden card.\n", cHand);
	if ((cHand + sCard == 21) && (pHand == 21)) {
		printf("You and the dealer both get a natural blackjack! Push.\n");
		return(pBank);
	} else if (pHand == 21) {
		printf("You got a natural blackjack! You win.\n");
		gameRecord[0]++;
		gameRecord[2]++;
		return(pBank + (1.5 * bet));
	}
	
	while (status == 1) {
		printf("Do you want to stand or hit(1 = Stand, 2 = Hit)? ");
		scanf("%d", &action);
		if (action == 1) {
			printf("The dealer's secret card is a(n) %d.\n", sCard);
			cHand += sCard;
			printf("The dealer has a score of %d.\n", cHand);
			while (cHand < 17) {
				temp = dealing();
				printf("The dealer got a(n) %d.\n", temp);
				cHand += temp;
				printf("The dealer has a score of %d.\n", cHand);
			}
			if (cHand > 21) {
				printf("Dealer busts! You win.\n");
				gameRecord[0]++;
				return(pBank + bet);
			} else if (cHand == 21) {
				printf("Dealer hit blackjack! Dealer wins.\n");
				gameRecord[1]++;
				return(pBank - bet);
			} else {
				status = 0;
			}
		} else if (action == 2) {
			temp = dealing();
			printf("You got a(n) %d.\n", temp);
			pHand += temp;
			printf("You have a score of %d.\n", pHand);
			if (pHand > 21) {
				printf("You bust! Dealer wins.\n");
				gameRecord[1]++;
				gameRecord[3]++;
				return(pBank - bet);
			} else if (pHand == 21) {
				printf("You hit blackjack! You win.\n");
				gameRecord[0]++;
				gameRecord[2]++;
				return(pBank + bet);
			}
		} else {
			printf("Please enter a valid input.\n");
			continue;
		}
	}
	printf("You: %d\nDealer: %d\n", pHand, cHand);
	if (pHand > cHand) {
		printf("You win!\n");
		gameRecord[0]++;
		return(pBank + bet);
	} else if (pHand < cHand) {
		printf("Dealer wins!\n");
		gameRecord[1]++;
		return(pBank - bet);
	} else if (pHand == cHand) {
		printf("Push!\n");
		return(pBank);
	}
	printf("End of game.\n");
	status = 0;
}