--<ScriptOptions statementTerminator=";"/>

CREATE TABLE oriimageinfo (
	id INT  primary key auto_increment NOT NULL,
	fileName VARCHAR(50) NOT NULL,
	fileType VARCHAR(50) DEFAULT "JPEGͼ��",
	fileDecimion VARCHAR(50) NOT NULL,
	filePath VARCHAR(256) NOT NULL,
	fileSize VARCHAR(32) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE decodejpginfo (
	id INT   primary key auto_increment NOT NULL,
	imageWidth INT NOT NULL,
	imageHeight INT NOT NULL,
	samplePrecision VARCHAR(32) NOT NULL,
	version VARCHAR(32) NOT NULL,
	sampleFactor VARCHAR(32) NOT NULL,
	scanComNum INT NOT NULL,
	dhtNum INT NOT NULL,
	dqtNum INT NOT NULL
) ENGINE=InnoDB;

