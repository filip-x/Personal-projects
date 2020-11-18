#pragma once
#pragma once
#include <QAbstractTableModel>
#include "repository_inherit.h"
#include <QWidget>
#include <QWindow>
#include <QTableView>
#include "observator.h"
#include <QStyledItemDelegate>
#include "my_list_repo.h"
class My_list_model : public QAbstractTableModel
{
private:
	Repo_my_list& storage;
public:
	My_list_model(Repo_my_list& storage, QObject* Parent = NULL);
	~My_list_model();
	int rowCount(const QModelIndex& parent = QModelIndex{}) const override;
	int columnCount(const QModelIndex& parent = QModelIndex{}) const override;
	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override;
	QVariant headerData(int section, Qt::Orientation orientation, int role = Qt::DisplayRole) const override;
	bool setData(const QModelIndex& index, const QVariant& value, int role = Qt::EditRole) override;
	Qt::ItemFlags flags(const QModelIndex& index) const override;
	void reset();
};


class My_list_window : public QWidget, public Observator
{
	Q_OBJECT
private:
	Repo_my_list& storage;

	void setup();
public:
	My_list_model* table_model;
	QTableView* table_view;
	My_list_window(Repo_my_list&, QWidget* = NULL);
	~My_list_window() override;
	void update() override;
	void closeEvent(QCloseEvent*);
};