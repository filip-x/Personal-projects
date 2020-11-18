#pragma once
#include"tape.h"
#include "repository_inherit.h"
class Action
{
public:
	virtual ~Action() = 0 {};
	virtual void undo() = 0;
	virtual void redo() = 0;
	
};

class Add_action:public Action
{
private:
	Tape* element;
	Repository_Inherit* storage;
public:
	Add_action(Tape*, Repository_Inherit* storage);
	~Add_action();
	void undo();
	void redo();
};



class Remove_action:public Action
{
private:
	Tape* element;
	Repository_Inherit* storage;
public:
	Remove_action(Tape*, Repository_Inherit* storage);
	~Remove_action();
	void undo();
	void redo();
};



class Update_action:public Action
{
private:
	Tape* oldElement;
	Tape* newElement;
	Repository_Inherit* storage;
public:
	Update_action(Tape*, Tape*, Repository_Inherit* storage);
	~Update_action();
	void undo();
	void redo();
};

