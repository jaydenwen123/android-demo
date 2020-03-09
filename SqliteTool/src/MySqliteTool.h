/*
 * MySqliteTool.h
 *
 *  Created on: 2017-4-4
 *      Author: Administrator
 */

#ifndef MYSQLITETOOL_H_
#define MYSQLITETOOL_H_

namespace std {

class MySqliteTool {
public:
	MySqliteTool();
	virtual ~MySqliteTool();

	int getAge() const {
		return age;
	}

	void setAge(int age) {
		this->age = age;
	}

	string getName() const {
		return name;
	}

	void setName(string name) {
		this->name = name;
	}

	int getSalary() const {
		return salary;
	}

	void setSalary(int salary) {
		this->salary = salary;
	}

private:
	string name;
	int age;
	int salary;
};
} /* namespace std */
#endif /* MYSQLITETOOL_H_ */
