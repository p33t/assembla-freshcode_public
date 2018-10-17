import * as React from 'react';

interface IProps {
    countBy?: number;
}

interface IState {
    count: number;
}

class Description extends React.Component<IProps, IState> {
    public static defaultProps: Partial<IProps> = {
        countBy: 1,
    };

    public state: IState = {
        count: 0,
    };

    public increase = () => {
        // Would be nice to break into a const but not good enough to handle type decl.
        this.setState((state) => ({ count: state.count + this.props.countBy! }));
    };

    public render() {
        return (
            <div>
                <p>My favorite number is {this.state.count}</p>
                <button onClick={this.increase}>Increase</button>
            </div>
        );
    }
}

export default Description;