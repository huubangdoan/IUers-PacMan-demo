
export class MainMenuPanel {
    /**
     * @param {{ handleAction: (command: string) => void }} controller
     */
    constructor(controller) {
        this.controller = controller;
        this.element = document.createElement('div');
        this.element.className = 'main-menu-panel';
        this._applyPanelStyle();
        this._addBackground('assets/image/Menu Graphics/bgr1.png', 0, 0, 672, 672);
        this._addButton({
            src: 'assets/image/Menu Graphics/start.png',
            x: 231, y: 190, width: 210, height: 120,
            actionCommand: 'Start',
        });

        this._addButton({
            src: 'assets/image/Menu Graphics/gacha.png',
            x: 231, y: 275, width: 210, height: 120,
            actionCommand: 'Gacha',
        });

        this._addButton({
            src: 'assets/image/Menu Graphics/skin.png',
            x: 231, y: 360, width: 210, height: 120,
            actionCommand: 'Skin',
        });

        this._addButton({
            src: 'assets/image/Menu Graphics/quit.png',
            x: 231, y: 445, width: 210, height: 120,
            actionCommand: 'Quit',
        });

        this._addButton({
            src: 'assets/image/Menu Graphics/settings.png',
            x: 580, y: 15, width: 95, height: 50,
            actionCommand: 'Settings',
        });
    }
    _applyPanelStyle() {
        Object.assign(this.element.style, {
            position: 'relative',
            width: '672px',
            height: '672px',
            overflow: 'hidden',
        });
    }

    /** Tương đương: ImageIcon bgIcon + JLabel background + setBounds + add(background) */
    _addBackground(src, x, y, width, height) {
        const bg = document.createElement('img');
        bg.src = src;
        bg.alt = 'background';
        Object.assign(bg.style, {
            position: 'absolute',
            left: `${x}px`,
            top: `${y}px`,
            width: `${width}px`,
            height: `${height}px`,
            zIndex: '0',
            pointerEvents: 'none', // nền không nhận click, giống JLabel phía dưới
        });
        this.element.appendChild(bg);
    }

    /**
     * Tương đương: tạo ImageIcon (scaled), JButton, makeButtonTransparent,
     * setupZoomEffect, setActionCommand, addActionListener, add(button)
     */
    _addButton({ src, x, y, width, height, actionCommand }) {
        const button = document.createElement('button');
        button.type = 'button';
        button.className = 'menu-button';
        button.setAttribute('data-action', actionCommand);

        Object.assign(button.style, {
            position: 'absolute',
            left: `${x}px`,
            top: `${y}px`,
            width: `${width}px`,
            height: `${height}px`,
            backgroundImage: `url('${src}')`,
            backgroundSize: 'contain',
            backgroundRepeat: 'no-repeat',
            backgroundPosition: 'center',
            border: 'none',
            padding: '0',
            cursor: 'pointer',
            zIndex: '1',
            // makeButtonTransparent: bỏ nền/viền mặc định của button
            backgroundColor: 'transparent',
            outline: 'none',
            transition: 'transform 0.15s ease-in-out', // setupZoomEffect
        });

        // setupZoomEffect: phóng to nhẹ khi hover
        button.addEventListener('mouseenter', () => {
            button.style.transform = 'scale(1.08)';
        });
        button.addEventListener('mouseleave', () => {
            button.style.transform = 'scale(1)';
        });

        // addActionListener(controller): gọi controller khi click
        button.addEventListener('click', () => {
            if (this.controller && typeof this.controller.handleAction === 'function') {
                this.controller.handleAction(actionCommand);
            }
        });

        this.element.appendChild(button);
    }

    /** Trả về DOM element gốc để gắn vào trang, tương đương việc add JPanel vào JFrame */
    getElement() {
        return this.element;
    }
}