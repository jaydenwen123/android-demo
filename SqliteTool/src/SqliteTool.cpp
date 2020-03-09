//============================================================================
// Name        : SqliteTool.cpp
// Author      : wenxiaofei
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <sqlite3.h>
#include <iostream>
#include "MySqliteTool.h"
using namespace std;

int main(int argc, char **argv) {
	MySqliteTool sqlitetool;
	sqlitetool.setAge(3);
	sqlitetool.setName("wenxiaofei");
	sqlitetool.setSalary(2343);
	cout << sqlitetool.getName() << "," << sqlitetool.getAge() << endl;
	cout << "**********************************" << endl;


	return 0;
}
