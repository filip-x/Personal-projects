#include "service.h"

Service::Service(Repository_Inherit* recordings,Repo_my_list* my_list_service)
{
	this->recordings = recordings;
	this->my_list = my_list_service;
}

Service::~Service()
{
	delete this->recordings;
}

void Service::creating_the_iterator()
{
	this->recordings->Create_Iterator();
}

void Service::append_function(string title, string section, Date date_of_creation, int access_count, string footage_preview)
{
	if (this->recordings->repository_search(title) != NULL)
	{
		throw Exceptions("It exists mate!");
	}
	Tape* adding_tape = new Tape(title, section, date_of_creation, access_count, footage_preview);
	this->recordings->repository_add(adding_tape);
	unique_ptr<Add_action> action = make_unique<Add_action>(adding_tape, recordings); // new Add_action(adding_tape);
	undo_stack.push_back(move(action));
	redo_stack.empty();
}

void Service::delete_function(string title)
{
	if (this->recordings->repository_search(title) == NULL)
	{
		throw Exceptions("It dose not exist mate!");
	}
	Tape* title_delete = this->recordings->repository_search(title);
	this->recordings->repository_delete(title);
	unique_ptr<Remove_action> action = make_unique<Remove_action>(title_delete, recordings); // new Add_action(adding_tape);
	undo_stack.push_back(move(action));
	redo_stack.empty();
	delete title_delete;
}

void Service::update_function(string title, string section, Date date_of_creation, int access_count, string footage_preview)
{
	if (this->recordings->repository_search(title) == NULL)
	{
		throw Exceptions("It dose not exist mate!");
	}
	Tape* old_element = this->recordings->repository_search(title);
	Tape* new_element = new Tape(title,section, date_of_creation, access_count, footage_preview);
	this->recordings->repository_update(title, section, date_of_creation, access_count, footage_preview);
	unique_ptr<Update_action> action = make_unique<Update_action>(old_element,new_element,recordings); // new Add_action(adding_tape);
	undo_stack.push_back(move(action));
	redo_stack.empty();
	delete old_element;
	delete new_element;
}
std::vector<Tape*> Service::list_function()
{
	return this->recordings->get_array_for_listing();
}

Tape* Service::next_tape()
{
	Tape* current_element_display;
	current_element_display = recordings->current_tape();
	recordings->next_tape();
	return current_element_display;
}

void Service::save_tape_by_title(string title)
{
	Tape* saver;
	if (this->recordings->repository_search(title) == NULL)
	{
		throw Exceptions("It dose not exist mate!");
	}
	saver = recordings->repository_search(title);
	my_list->saving_my_list(saver);

}

std::vector<Tape*> Service::list_my_list()
{
	return my_list->display_my_list();
}

std::vector<Tape*> Service::list_by_section_access(string section,int access_count)
{
	return this->recordings->list_section_access_count(section,access_count);
}

void Service::open_my_list()
{
	my_list->open();
}

void Service::undo()
{
	if (undo_stack.size() > 0)
	{
		auto Element = move(undo_stack.back());
		undo_stack.pop_back();
		Element->undo();
		redo_stack.push_back(move(Element));
	}
	else
		throw Exceptions("Nothing to undo!");
}

void Service::redo()
{
	if (redo_stack.size() > 0)
	{
		auto Element = move(redo_stack.back());
		redo_stack.pop_back();
		Element->redo();
		undo_stack.push_back(move(Element));
	}
	else
		throw Exceptions("Nothing to redo!");
}








