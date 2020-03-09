/*
 * Protocol.h
 *
 *  Created on: 2017-5-7
 *      Author: Administrator
 */
#define HEADLEN 2
#define CONTROLLEN 17
#define ENDLEN 3
#define DATALENLENTHG 2
//#define HEADDEFAULT {0x61,0x74}
#ifndef PROTOCOL_H_
#define PROTOCOL_H_


class Protocol {
public:
	// the constructor method..
	Protocol();
	virtual ~Protocol();
	Protocol::Protocol(short datalen,char *control,char *data,bool lorb);
	Protocol::Protocol(char * head,short datalen,char *control,char *data,char *end,bool lorb);

// the getter and setter method...
	void setLorb(bool lorb = false);
	bool isLorb() const;
	const char* getHead() ;
	void setHead(char *head);
	const char* getEnd() ;
	void setEnd(char *end);
	short getDatalen() const;
	void setDatalen(short datalen);
	const char* getData() ;
	void setData(char *data);
	void setChecksum(short checksum) ;
	short getChecksum() const ;
	const char* getControl() ;
	void setControl(char *control);
	//to generate the checksum
	void generateCheckSum();
private:
	//to define the protocol attribute
	// the frame head
	char head[HEADLEN];
	// the data length
	short datalen;
	// control
	char control[CONTROLLEN];
	// the content
	char data[datalen];
	// the checksum :to check
	short checksum;
	// the frame end;
	char end[ENDLEN];
	// is big or little
	bool lorb = false;
};

#endif /* PROTOCOL_H_ */
