#include "mylist_display.h"
#include <QFont>
#include <QBrush>
#include <QTableView>
#include <QLayout>
#include "Observator.h"
#include <QPainter>
My_list_model::My_list_model(Repo_my_list& storage, QObject* Parent) : QAbstractTableModel(Parent), storage(storage) { }

My_list_model::~My_list_model() { }

inline void clear_vector(std::vector<Tape*> data)
{
	for (auto element : data)
		delete element;
}

int My_list_model::rowCount(const QModelIndex& parent) const
{
	auto data = storage.display_my_list();
	int count = data.size();
	clear_vector(data);
	return count;
}

int My_list_model::columnCount(const QModelIndex& parent) const
{
	return 5;
}

inline string date_to_string(Date date_to_display)
{
	char string_date[100];
	sprintf(string_date, "%d-%d-%d", date_to_display.tm_mon, date_to_display.tm_mday, date_to_display.tm_year);
	return string(string_date);
}
QVariant My_list_model::data(const QModelIndex& index, int role) const
{
	int row = index.row();
	int column = index.column();
	auto data = storage.display_my_list();
	if (row == data.size())
	{
		clear_vector(data);
		return QVariant();
	}

	Tape* element = data[row];
	if (role == Qt::DisplayRole || role == Qt::EditRole)
	{
		QString information;
		switch (column)
		{
		case 0:
			information = QString::fromStdString(element->get_title());
			clear_vector(data);
			return information;
		case 1:
			information = QString::fromStdString(element->get_section());
			clear_vector(data);
			return information;
		case 2:
			information = QString::fromStdString(date_to_string(element->get_date_of_creation()));
			clear_vector(data);
			return information;
		case 3:
			information = QString::fromStdString(std::to_string(element->get_access_count()));
			clear_vector(data);
			return information;
		case 4:
			information = QString::fromStdString(element->get_footage_preview());
			clear_vector(data);
			return information;
		default: break;
		}
	}
	clear_vector(data);
	return QVariant{};

}

QVariant My_list_model::headerData(int section, Qt::Orientation orientation, int role) const
{
	if (role == Qt::DisplayRole)
	{
		if (orientation == Qt::Horizontal)
		{
			switch (section)
			{
			case 0:
				return QString{ "Title" };
			case 1:
				return QString{ "Section" };
			case 2:
				return QString{ "Date of Creation" };
			case 3:
				return QString{ "Access Count" };
			case 4:
				return QString{ "Footage Preview" };
			default:
				break;
			}
		}
	}

	return QVariant{};
}

bool My_list_model::setData(const QModelIndex& index, const QVariant& value, int role)
{
	return false;
}

Qt::ItemFlags My_list_model::flags(const QModelIndex& index) const
{
	return Qt::ItemFlags();
}

void My_list_model::reset()
{
	this->beginResetModel();
	this->endResetModel();
}

void My_list_window::setup()
{
	table_view = new QTableView;
	table_view->setModel(table_model);
	table_view->resizeColumnsToContents();
	this->setLayout(new QVBoxLayout());
	this->layout()->addWidget(table_view);
	this->setFixedSize(438, 200);
	this->show();
}

My_list_window::My_list_window(Repo_my_list& storage, QWidget* Parent) : storage(storage), QWidget(Parent)
{
	table_model = new My_list_model(storage);
	setup();
}
My_list_window::~My_list_window()
{

}

void My_list_window::update()
{
	table_model->reset();
}

void My_list_window::closeEvent(QCloseEvent* Event)
{
	this->setObjectName(this->objectName() + "_closed");
	QWidget::closeEvent(Event);
}
