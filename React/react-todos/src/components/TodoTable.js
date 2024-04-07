import TodoRowItem from './TodoRowItem';

function TodoTable(props) {
    return (
        <tabele className="table table-hover">
            <thread>
                <tr>
                    <th scope='col'>#</th>
                    <th scope='col'>Description</th>
                    <th scope='col'>Assigned</th>
                </tr>
            </thread>
            <tbody>
                {props.todos.map(todo => (
                    <TodoRowItem
                        rowNumber={todo.rowNumber}
                        rowDescription={todo.rowDescription}
                        rowAssigned={todo.rowAssigned}
                    />
                ))}  
            </tbody>
        </tabele>
    )
}

export default TodoTable