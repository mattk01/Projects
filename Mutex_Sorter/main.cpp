#include <iostream>
#include <fstream>
#include <thread>
#include <mutex>
#include <sstream>
using namespace std;

/*
 * Project: Homework 3 Part 3
 * Author: Matt Kinderman
 * This program uses the mutex to put a file of numbers ranging from 0-99 into 1 of 10 bins.
 * Each bin represents 10 numbers: 0-9, 10-19, ..., 90-99.
 */

int arr[100] = {0};
int binMin = 0;
int binMax = 10;
mutex m;

void getBinCount() {
    m.lock();
    int binCount = 0;
    for(int j = binMin; j < binMax; j++){
        binCount+= arr[j];
    }
    string outputFileAddress = "Output.txt"; // Used for non-mac
    //string outputFileAddress = "/Users/mattkinderman/Documents/Junior_Year/Comp_3500/Homework3_3/Output.txt"; // Used for mac
    ofstream write;
    write.open(outputFileAddress, ios::app);
    write << "Group " + to_string(binMin / 10 + 1) + " (" + to_string(binMin) + "..."
             + to_string(binMax - 1) + ") Count: " + to_string(binCount) + "\n";

    binMin += 10;
    binMax += 10;
    m.unlock();
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

int main() {

    //string inputFileAddress = "/Users/mattkinderman/Documents/Junior_Year/Comp_3500/Homework3_3/HW3.txt"; // Used for mac
    string inputFileAddress = "HW3.txt"; // Used for non-mac
    fillBins(inputFileAddress);

    thread t1(getBinCount);
    thread t2(getBinCount);
    thread t3(getBinCount);
    thread t4(getBinCount);
    thread t5(getBinCount);
    thread t6(getBinCount);
    thread t7(getBinCount);
    thread t8(getBinCount);
    thread t9(getBinCount);
    thread t10(getBinCount);

    t1.join();
    t2.join();
    t3.join();
    t4.join();
    t5.join();
    t6.join();
    t7.join();
    t8.join();
    t9.join();
    t10.join();

    return 0;
}