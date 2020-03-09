/*
 * Protocol.cpp
 *
 *  Created on: 2017-5-7
 *      Author: Administrator
 */

#include <string>
#include <cstring>
#include "Protocol.h"
const static char headDefault[HEADLEN]={0x61,0x74};
const static char endDefault[ENDLEN]={0x65,0x6E,0x64};
Protocol::Protocol() {
}
Protocol::~Protocol() {

}
Protocol::Protocol(short datalen, char *control, char *data, bool lorb) {
	this->datalen = datalen;
	this->control = control;
	this->data = data;
	this->lorb = lorb;
	this->generateCheckSum();
	strncpy(this->head,headDefault,HEADLEN);
	strncpy(this->end,endDefault,ENDLEN);
}
Protocol::Protocol(char * head, short datalen, char *control, char *data,
		char *end, bool lorb) {
	this->head = head;
	this->datalen = datalen;
	this->control = control;
	this->data = data;
	this->end = end;
}
short Protocol::getChecksum() const {
	return checksum;
}

void Protocol::setChecksum(short checksum) {
	this->checksum = checksum;
}

const char* Protocol::getControl() {
	return control;
}

void Protocol::setControl(char * control){
	strcpy(this->control,control,CONTROLLEN);
}

const char* Protocol::getData() {
	return data;
}
void Protocol::setData(char *data){
	strncpy(this->data,data,datalen);

}

short Protocol::getDatalen() const {
	return datalen;
}

void Protocol::setDatalen(short datalen) {
	this->datalen = datalen;
}

const char* Protocol::getEnd() {
	return end;
}
void Protocol::setEnd(char *end){
	strncpy(this->end,end,ENDLEN);

}

const char* Protocol::getHead() {
	return head;
}

void Protocol::setHead(char *head){
	strncpy(this->head,head,HEADLEN);
}

bool Protocol::isLorb() const {
	return lorb;
}

void Protocol::setLorb(bool lorb = false) {
	this->lorb = lorb;
}

void Protocol::generateCheckSum(){
	short checksum=0;
	// for test ,at first the checkSum is get from data content
	int total=datalen;
	for(int i=0;i<total;i++){
		checksum+=data[i];
	}
	this->checksum=checksum;
}

