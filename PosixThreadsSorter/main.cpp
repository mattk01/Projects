#include <iostream>
#include <fstream>
#include <pthread.h>
#include <sstream>

/*
 * Author: Matt Kinderman
 * This program uses POSIX threads to put a file of numbers ranging from 0-99 into 1 of 10 bins.
 * Each bin represents 10 numbers: 0-9, 10-19, ..., 90-99.
 */

using namespace std;

int arr[100] = { 0 };


void* getBinCount(void * arg){
    int *iptr = (int *)arg;
    int binMin = *iptr * 10;
    int binMax = *iptr * 10 + 10;
    int binCount = 0;
    string line; // Used to save each line of file

    for(int j = binMin; j < binMax; j++){
            binCount+= arr[j];
        }

    string outputFileAddress = "Output.txt"; // Used for non-mac
    //string outputFileAddress = "/Users/mattkinderman/Documents/Junior_Year/Comp_3500/Homework3_2/Output.txt"; // Used for mac
    ofstream write;
    write.open(outputFileAddress, ios::app);
    write << "Group " + to_string(binMin / 10 + 1) + " (" + to_string(binMin) + "..."
        + to_string(binMax - 1) + ") Count: " + to_string(binCount) + "\n";

    return NULL;
}

void fillBins(string fileAddress){
    int numVal;
    ifstream myFile(fileAddress);

    for (string line; getline(myFile, line);) { // Iterate each line of myFile
        stringstream ss(line);
        while (ss >> numVal) {
            arr[numVal]++;
        }
    }
    myFile.close();
}

int main(){
    pthread_t newThread;
    //string inputFileAddress = "/Users/mattkinderman/Documents/Junior_Year/Comp_3500/Homework3_2/HW3.txt"; // Used for mac
    string inputFileAddress = "input.txt"; // Used for non-mac
    fillBins(inputFileAddress);

    for (int i = 0; i < 10; i++) { // Find count for each group of numbers.
        pthread_create(&newThread, NULL, getBinCount, &i);
        pthread_join(newThread, NULL);
    }
}
