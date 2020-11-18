#include"action.h"

Add_action::Add_action(Tape* tape_element, Repository_Inherit* storage)
{
	element = new Tape(tape_element);
	this->storage = storage;
}

Add_action::~Add_action()
{
	delete element;
}

void Add_action::undo()
{
	storage->repository_delete(element->get_title());
}

void Add_action::redo()
{
	storage->repository_add(new Tape(element));
}

Remove_action::Remove_action(Tape* tape_element, Repository_Inherit* storage)
{
	element = new Tape(tape_element);
	this->storage = storage;
}

Remove_action::~Remove_action()
{
	delete element;
}

void Remove_action::undo()
{
	storage->repository_add(new Tape(element));

}

void Remove_action::redo()
{
	storage->repository_delete(element->get_title());
}

Update_action::Update_action(Tape* old_tape_element, Tape* new_tape_element, Repository_Inherit* storage)
{
	oldElement = new Tape(old_tape_element);
	newElement = new Tape(new_tape_element);
	this->storage = storage;
}

Update_action::~Update_action()
{
	delete oldElement;
	delete newElement;
}

void Update_action::undo()
{
	storage->repository_update(oldElement->get_title(),oldElement->get_section(),oldElement->get_date_of_creation(),oldElement->get_access_count(),oldElement->get_footage_preview());

}

void Update_action::redo()
{
	storage->repository_update(newElement->get_title(), newElement->get_section(), newElement->get_date_of_creation(), newElement->get_access_count(), newElement->get_footage_preview());
}
